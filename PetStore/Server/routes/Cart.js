/*
get, post, put, delete
*/

const express = require("express");
const app = express.Router();
const utils = require("../utils");
const db = require("../db");

app.get("/display", (request, response) => {
  //const customerID = request.params
 
  const statement = `
  SELECT 
  c.cartID, c.productID, c.quantity, c.mrp, c.discountedPrice, c.total,
  p.title, p.category, p.company, p.image
FROM 
  cart c
JOIN 
  product p ON c.productID = p.productID
WHERE
  c.customerID = ?

    `;
//console.log(request);
//console.log("request.user ",request.user);
    //[request.user.id]
  db.execute(statement,[request.user.id], (error, items) => {
    console.log(items)
    response.send(utils.createResponse(error, items));
    // console.log(cartID+ ` `+ productID+ ` `+ quantity+ ` `+ mrp+ ` `+ discountedPrice+ ` `+ total+ ` `+ title+ ` `+ category+ ` `+ company+ ` `+ image);


  });
});

app.post("/add", (request, response) => {
  const { customerID, productID, quantity, mrp, discountedPrice, total } =
    request.body;
  const statement = `
    insert into cart 
        (customerID, productID, quantity, mrp, discountedPrice, total )
    values
        (?, ?, ?, ?, ?, ?)           
    `;
  db.execute(
    statement,
    [customerID, productID, quantity, mrp, discountedPrice, total],
    (error, items) => {
      //console.log(error);
      response.send(utils.createResponse(error, items));
      //console.log(`============` + items);
    }
  );
});

app.put("/update", (request, response) => {
  //const { cartID } = request.params;
  const { quantity, mrp, discountedPrice, total } = request.body;
  const statement = `
      update cart 
        set
            quantity = ?, 
            mrp = ?, 
            discountedPrice = ?, 
            total = ?
        where   
        cartID = ?
      `;
  db.execute(
    statement,
    [quantity, mrp, discountedPrice, total, request.user.id],
    (error, items) => {
      response.send(utils.createResponse(error, items));
    }
  );
});

app.delete("/:cartID", (request, response) => {
  const { cartID } = request.params;
  const statement = `
        delete from cart 
          where   
          cartID = ?
        `;
  db.execute(statement, [cartID], (error, items) => {
    response.send(utils.createResponse(error, items));
  });
});

module.exports = app;
