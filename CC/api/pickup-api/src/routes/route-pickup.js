const router = require('express').Router();
const { pickup } = require('../controllers');

// POST localhost:8080/pickup/add => Tambah data pickup ke database
router.post('/pickup/add', pickup.addDataPickup);

// PATCH localhost:8080/pickup/edit/ => Edit data pickup berdasarkan id
router.patch('/pickup/edit', pickup.editDataPickup);

module.exports = router;