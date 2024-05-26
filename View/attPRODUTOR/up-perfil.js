const express = require('express');
const mysql = require('mysql');

const app = express();
const port = 3000;

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'DB_ICAROS'
});

connection.connect();

app.get('/usuario/:id', (req, res) => {
  const userId = req.params.id;
  const query = `SELECT * FROM usuario WHERE id = ${userId}`;
  
  connection.query(query, (error, results, fields) => {
    if (error) throw error;
    res.json(results);
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`);
});