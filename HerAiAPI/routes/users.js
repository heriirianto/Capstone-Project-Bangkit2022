var express = require('express');
var router = express.Router();
var connection = require("../lib/db")

/* GET users listing. */
router.get('/', function(req, res, next) {
  connection.query('SELECT * FROM user;',function(err,rows)     {

    if(err) {
        req.flash('error', err);
        res.send({data:''});
    } else {
        res.send({data:rows});
    }
});
});

router.get('/:id', function(req,res){
  let id = req.params.id;
  connection.query('SELECT * FROM user WHERE user_id = ?;', [id], function (error, results) {
      if(error) throw error;  
      res.send({ 
          success: true, 
          message: 'Berhasil ambil data!',
          data: results
      });
  });
});

router.patch('/edit', function(req,res){
  let dataEdit = {
      username : req.body.username,
      user_fullname : req.body.fullname,
      user_password : req.body.password,
      user_email : req.body.email
  }
  let id = req.body.id
  connection.query('UPDATE user SET ? WHERE user_id = ?;', [dataEdit, id], function (error, results) {
      if(error) throw error;  
      res.send({ 
          success: true, 
          message: 'Berhasil edit data!',
      });
  });
});

//Made by Samuel
router.post('/email', function(req, res, next) {
  var email = req.body.email;
  connection.query('SELECT * FROM user WHERE user_email = ?', email, function(err, rows){

    if(err) {
      req.flash('error', err);
    } 
    
    if(rows.length == 0){
      connection.query('INSERT INTO user (user_email) VALUES (?)', email, function(err, result){
        res.send({id: result.insertId});
      });
      return;
    }

    res.send({id: rows[0].user_id})
});
});

module.exports = router;
