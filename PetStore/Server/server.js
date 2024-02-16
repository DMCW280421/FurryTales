const express = require('express')
const cors = require('cors')
const morgan = require('morgan')
const utils = require('./utils')
const jwt = require('jsonwebtoken')
const config = require('./config') 

const app = express()
app.use(cors())
app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.use(morgan('combined'))
app.use(express.static('images'))


app.get("/version", (request, reaponse) => {
  reaponse.send(utils.createSuccessResponse("1.0"));
});

// check if token is required
app.use((request, response, next) => {
  const skipTokenUrls = [
    "/admin/register",
    "/admin/login",
    "/customer/register",
    "/customer/login"
  ];
  if (
    skipTokenUrls.findIndex((item) => {
      return request.url.startsWith(item);
    }) != -1
  ) {
    next();
  } else {
    const token = request.headers["token"];
    console.log(`token: ${token}`);
    if (!token) {
      response.send(utils.createErrorResponse("Token Missing"));
    } else {
      try {
        const payload = jwt.verify(token, config.secrete);
        console.log("payload ",payload);

        request.user = {
          id: payload["id"],
          name: payload["name"],
          type: payload["type"],
        };
        next();
      } catch (ex) {
        console.log(ex);
        response.send(utils.createErrorResponse("invalid Token"));
      }
    }
  }
});

const adminRoute = require('./routes/Admin')
const customerRoute = require('./routes/Customer')
const orderRoute  = require('./routes/Order')
const productRoute = require('./routes/Product')
const cartRoute = require('./routes/Cart')
// const orderDetailsRoute = require('./routes/OrderDetails')
// const reviewRoute = require('./routes/Review')
// const paymentDetailsRoute = require('./routes/PaymentDetails')
// const deliveryDetailsRoute = require('./routes/DeliveryDetails')



app.use('/admin', adminRoute)
app.use('/customer', customerRoute)
app.use('/order', orderRoute)
app.use('/product', productRoute)
app.use('/cart', cartRoute)
// app.use('/orderDetails', orderDetailsRoute)
// app.use('/review', reviewRoute)
// app.use('/paymentDetails', paymentDetailsRoute)
// app.use('/deliveryDetails', deliveryDetailsRoute)


app.listen(5000, () => {
  console.log("Server started @5000");
});
