const express = require('express')
const app = express.Router()
const utils = require('../utils')
const db = require('../db')
const multer = require('multer')
const upload = multer({ dest:'images' })

app.post('/add/:adminID', upload.single('image'), (request,response)=>{
    const { title, description, mrp, discount, company, category } = request.body

    const adminID = request.params

    console.log("adminID+++++"+adminID)

    const statement = `insert into product (title, description, mrp, discount, company, category, image) values (?,?,?,?,?,?,?)`

    db.execute(statement, [title, description, mrp, discount, company, category, request.file.filename], (error, result)=>{
        response.send(utils.createResponse(error, result))
        
    })
})

app.delete('/:productID', (request, response)=>{
    const { productID } = request.params
    const statement = `delete from product where productID = ?`
    db.execute(statement, [productID], (error, result) =>{
        response.send(utils.createResponse(error, result))
    })
})

app.put('/:productID', (request, response) => {
    const { productID } = request.params
    const { description, mrp, discount, company, category } = request.body
    const statement = `
      update product
        set
          description = ?, 
          mrp = ?, 
          discount = ?, 
          company = ?, 
          category = ?
        where
        productID = ?
    `
    console.log([description, mrp, discount, company, category, productID])
    db.execute(
      statement,
      [description, mrp, discount, company, category, productID],
      (error, result) => {
        console.log(error)
        response.send(utils.createResponse(error, result))
      }
    )
  })

app.get('/search', (request, response)=>{
    let statement = `select title, description, mrp, discount, company, category, image from product`
console.log("+++++++++"+statement)
    // const parameters = []
    // if(request.user.type == 'admin'){
    //     statement += ` where adminID = ?`
    //     parameters.push(request.user.id)
       
    // }
    db.execute(statement, (error, result)=>{
        response.send(utils.createResponse(error, result))
        
    })
})

module.exports = app