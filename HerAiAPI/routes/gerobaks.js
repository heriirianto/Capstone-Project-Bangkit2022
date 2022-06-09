var express = require('express');
var router = express.Router();
var connection = require("../lib/db")

async function insertGerobakData(userId) {
    return new Promise((resolve, reject) => {
        return connection.query('INSERT INTO gerobak (user_id) VALUES (?);', userId, (err, res) =>{
            if (err) reject(err)

            return resolve(res)
        })
    })
}

async function insertGerobakDetailData(gerobakId, item) {
    return new Promise((resolve, reject) => {
        return connection.query(
            'INSERT INTO gerobak_detail (gerobak_id, item_id, gdetail_item_amount) VALUES (?, ?, ?);', 
            [gerobakId, item.item_id, item.quantity]
        , (err, res) => {
            if (err) reject(err)

            return resolve(res)
        })
    })
}

async function updateGerobakPrice(gerobakId, weight, price) {
    return new Promise((resolve, reject) => {
        return connection.query(
            'UPDATE gerobak SET gerobak_weight = ?, gerobak_price = ? WHERE (gerobak_id = ?);', 
            [weight, price, gerobakId]
        , (err, res) => {
            if (err) reject(err)

            return resolve(res)
        })
    })
}

async function getCurrentWeightOfItem(itemId) {
    return new Promise((resolve, reject) => {
        return connection.query(
            'SELECT item_weight FROM item WHERE item_id = ?;', itemId, (err, res) => {
            if (err) reject(err)

            const { item_weight } = res[0]
            return resolve(item_weight)
        })
    })
}

//Made by Samuel
// Insert data into gerobak and gerobak_detail tables
router.post('/', async function(req, res, next) {
    var items = req.body.list;
    var user = req.body.user;

    const response = await insertGerobakData(user)
    const gerobakId = response.insertId
    const promises = items.map(async item => {
        await insertGerobakDetailData(gerobakId, item)
        const currentWeight = await getCurrentWeightOfItem(item.item_id)

        return currentWeight * item.quantity
    })

    const results = await Promise.all(promises)
    const totalWeight = results.reduce((partialSum, num) => partialSum + num, 0)
    const finalPrice = 3000 * totalWeight/1000; //Ini temp doang pake 3000, ubah harganya disini

    await updateGerobakPrice(gerobakId, totalWeight, finalPrice)

    res.send('Success')
});

module.exports = router;
