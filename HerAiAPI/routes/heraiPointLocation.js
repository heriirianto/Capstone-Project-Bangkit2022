var express = require('express');
var router = express.Router();
var connection = require("../lib/db")

// Get all HerAi Point details
router.get('/location', function(req,res){
    connection.query('SELECT * FROM herai_point;', function (error, results) {
        if(error) throw error;  
        res.send({ 
            success: true, 
            message: 'Berhasil ambil data!',
            data: results 
        });
    });
});

module.exports = router;