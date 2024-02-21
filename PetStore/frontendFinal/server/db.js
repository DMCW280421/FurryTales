const mysql = require('mysql2')

// create a pool of connections
const pool = mysql.createPool({
  host: 'localhost',
  user: 'W2_80421_Ayushi',
  password: '1309',
  database: 'eCommerceDB',
  waitForConnections: true,
  connectionLimit: 10,
  maxIdle: 10,
  idleTimeout: 60000,
  queueLimit: 0,
  enableKeepAlive: true,
  keepAliveInitialDelay: 0,
})

module.exports = pool
