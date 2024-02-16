/*
get post(register) put(update profile) delete(delete profile)
*/
const express = require('express')
const app = express.Router()
const db = require('../db')
const utils = require('../utils')
const crypto = require('crypto-js')
const jwt = require('jsonwebtoken')
const config = require('../config')

app.get('/profile',(request,response)=>{
    //const customerID = request.params
    const statement = `select name, phone, email, address from Customer where customerID=?`
    
    console.log(request.user);
    db.execute(statement, [request.user.id], (error, items) => {
        console.log(items)
        response.send(utils.createResponse(error, items));
        //console.log("Result: "+result);
    })
})

app.post('/register', (request,response)=>{
    const { name, phone, email, password, address } = request.body

    const statement=`insert into Customer (name, phone, email, password, address) values (?,?,?,?,?)`

    const encryptedPassword = String(crypto.SHA256(password))
    db.execute(statement, [name, phone, email, encryptedPassword, address], (error,result)=>{
       
        response.send(utils.createResponse(error, result))
        
    })
})

app.post("/login", (request, response) => {
    const { email, password } = request.body;
    const statement = `select customerID, name, phone, address from Customer where email = ? and password = ?`;
  
    const encryptedPassword = String(crypto.SHA256(password));
  
    db.query(statement, [email, encryptedPassword], (error, users) => {
      if (error) {
        response.send(utils.createErrorResponse(error));
      } 
      else {
        if (users.length == 0) {
            
          response.send(utils.createErrorResponse("Credentials not found"));
        } else {
          const user = users[0];
          const payload = {
            id: user["customerID"],
            name: user["name"],
            type: "customer",
          };
          console.log("Payload", payload);

  
          const token = jwt.sign(payload, config.secrete);
          console.log("token ", token);
          response.send(
            utils.createSuccessResponse({
              token,
        
              id:user["customerID"],
              name: user["name"],
            })
          );
        }
      }
    });
  });

module.exports = app