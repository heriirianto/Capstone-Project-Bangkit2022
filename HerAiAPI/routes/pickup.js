var express = require('express');
var router = express.Router();
var connection = require("../lib/db")

// Add initial pickup data
router.post('/add', function(req,res){
    let data = {
        gerobak_id : req.body.gerobakId,
        hpoint_id : req.body.hpointId,
        pickup_schedule : req.body.schedule,
        hcourier_id : req.body.courierId
    }
    connection.query('INSERT INTO pickup SET ?;', [data], function (error, results) {
        if(error) throw error;  
        res.send({ 
            success: true,
            message: 'Berhasil tambah data!',
        });
    });
});

router.patch('/edit', function(req,res){
    let dataEdit = {
        gerobak_id : req.body.gerobakId,
        hpoint_id : req.body.hpointId,
        pickup_schedule : req.body.schedule,
        hcourier_id : req.body.courierId
    }
    
    connection.query('SELECT pickup_id FROM pickup WHERE gerobak_id = ? ORDER BY pickup_id DESC LIMIT 1;', [dataEdit.gerobak_id], function (error, value) {
        if(error) throw error;
        connection.query('UPDATE pickup SET ? WHERE pickup_id = ?;', [dataEdit, value[0].pickup_id], function (error, results) {
            if(error) throw error;
            res.send({
                success: true,
                message: 'Berhasil edit data!',
            });
        });
    })
});

module.exports = router;