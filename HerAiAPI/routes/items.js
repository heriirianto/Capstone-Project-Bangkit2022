var express = require('express');
var router = express.Router();
var connection = require("../lib/db")

/* GET users listing. */
router.get('/', function(req, res, next) {
  connection.query('SELECT * FROM item;',function(err, results){
    if(err) {
        req.flash('error', err);
        res.send({data:''});
    } else {
        res.send({ 
            success: true, 
            message: 'Berhasil ambil data!',
            data: results 
        });
    }
});
});

module.exports = router;