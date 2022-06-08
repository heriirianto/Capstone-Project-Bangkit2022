const router = require('express').Router();
const { item } = require('../controllers');

// GET localhost:8080/item => Ambil data semua item
router.get('/item', item.getDataItem);

module.exports = router;