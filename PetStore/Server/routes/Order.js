// /*
// get post
// */
const express = require("express");
const app = express.Router();
const dbPromise = require("../db-promise");
const utils = require("../utils");

app.post('/', async (request, response)=>{
    var [items] = await dbPromise.query(`select * from cart`);
    console.log(items)

    //response.send('done')
    let total = 0
    for (var item of items) {
      //  console.log("-------"+item+total)
      total += item['total']
    }
   console.log("Total "+total)
    const [row] = await dbPromise.execute(
      `
      insert into orderDetails
          (customerId, total)
      values
          (?, ?)
    `, 
      [request.user.id, total]
    )
    // capture order Id
    const orderID = row['insertId']

        for (const item of items) {
           // console.log("------====-"+item[0]+total)
       
      await dbPromise.execute(
        `
          insert into orders
              (ID, productId, quantity, mrp, price, total)
          values
              (?, ?, ?, ?, ?, ?)
      `,
        [
            orderID,
          item['productId'],
          item['quantity'],
          item['mrp'],
          item['discountedPrice'],
          item['total'],
        ]
      )
    }
    await dbPromise.execute(`delete from cart where customerID = ?`, [
        request.user.id,
      ])
    
      response.send(utils.createSuccessResponse('done'))
  })
  
  
module.exports = app;
