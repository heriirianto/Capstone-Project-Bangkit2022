const config = require('../configs/database');
const mysql = require('mysql');
const pool = mysql.createPool(config);

pool.on('error',(err)=> {
    console.error(err);
});

module.exports ={
    // Tambah data pickup
    addDataPickup(req,res){
        let data = {
            gerobak_id : req.body.gerobakId,
            hpoint_id : req.body.hpointId,
            pickup_schedule : req.body.schedule,
            hcourier_id : req.body.courierId
        }
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                INSERT INTO pickup SET ?;
                `
            , [data],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil tambah data!',
                });
            });
            connection.release();
        })
    },
    // Update data pickup berdasarkan ID
    editDataPickup(req,res){
        let dataEdit = {
            gerobak_id : req.body.gerobakId,
            hpoint_id : req.body.hpointId,
            pickup_schedule : req.body.schedule,
            hcourier_id : req.body.courierId
        }
        let id = req.body.id
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                UPDATE pickup SET ? WHERE pickup_id = ?;
                `
            , [dataEdit, id],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil edit data!',
                });
            });
            connection.release();
        })
    },
}