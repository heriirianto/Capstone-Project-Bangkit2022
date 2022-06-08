var mysql = require('mysql');
var connection = mysql.createConnection({
    host                : 'localhost',
    user                : 'root',
    password            : '',
    database            : 'db_test1'
});

connection.connect(function(error){
	if(!!error) {
		console.log(error);
	} else {
		console.log('Server Berjalan di Port : 8080');
	}
});

module.exports = connection;