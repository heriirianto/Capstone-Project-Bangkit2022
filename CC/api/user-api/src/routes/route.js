const router = require('express').Router();
const { user } = require('../controllers');

// GET localhost:8080/user/2 => Ambil data user berdasarkan id 
router.get('/user/:id', user.getDataUserByID);

// PATCH localhost:8080/user/2 => Edit data user berdasarkan id
router.patch('/user/edit', user.editDataUser);

module.exports = router;