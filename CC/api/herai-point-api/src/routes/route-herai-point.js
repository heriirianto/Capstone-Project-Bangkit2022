const router = require('express').Router();
const { location } = require('../controllers');

// GET localhost:8080/location => Ambil data semua heraipoint berdasarkan location
router.get('/location/', location.getDataHeraiPointByLocation);

module.exports = router;