/*
register - post
login - post (select)
*/
const express = require("express");
const app = express.Router();
const db = require("../db");
const utils = require("../utils");
const crypto = require("crypto-js");
const jwt = require("jsonwebtoken");
const config = require("../config");

app.post("/register", (request, response) => {
  const { name, phone, email, password, address } = request.body;

  const statement = `insert into Admin (name, phone, email, password, address) values (?,?,?,?,?)`;

  const encryptedPassword = String(crypto.SHA256(password));

  db.execute(
    statement,
    [name, phone, email, encryptedPassword, address],
    (error, result) => {
      response.send(utils.createResponse(error, result));
    }
  );
});

app.post("/login", (request, response) => {
  const { email, password } = request.body;
  const statement = `select adminID, name, address from Admin where email = ? and password = ?`;

  const encryptedPassword = String(crypto.SHA256(password));

  db.execute(statement, [email, encryptedPassword], (error, users) => {
    if (error) {
      response.send(utils.createErrorResponse(error));
    } else {
      if (users.length == 0) {
        response.send(utils.createErrorResponse("Credentials not found"));
      } else {
        const user = users[0];
        const payload = {
          id: user["adminID"],
          name: user["name"],
          type: "admin",
        };

        const token = jwt.sign(payload, config.secrete);
        response.send(
          utils.createSuccessResponse({
            token,
            name: user["name"],
          })
        );
      }
    }
  });
});

module.exports = app;
