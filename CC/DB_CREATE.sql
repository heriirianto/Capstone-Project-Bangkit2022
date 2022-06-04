CREATE DATABASE db_test1;

USE db_test1;

CREATE TABLE user_details(
    udetails_id integer PRIMARY KEY AUTO_INCREMENT,
    user_id integer,
    udetails_address varchar(250),
    udetails_phonenumber varchar(20)
);

CREATE TABLE user(
    user_id integer PRIMARY KEY AUTO_INCREMENT,
    username varchar(25),
    user_fullname varchar(100),
    user_password varchar(20),
    user_email varchar(100)
);

CREATE TABLE item(
    item_id integer PRIMARY KEY AUTO_INCREMENT,
    item_name varchar(25),
    item_weight integer,
    item_details varchar(200),
    item_category_id integer
);

CREATE TABLE item_category(
    item_category_id integer PRIMARY KEY AUTO_INCREMENT,
    item_category_name varchar(25)
);

CREATE TABLE gerobak(
    gerobak_id integer PRIMARY KEY AUTO_INCREMENT,
    user_id integer,
    gerobak_weight integer,
    gerobak_price integer
);

CREATE TABLE gerobak_detail(
    gdetail_id integer PRIMARY KEY AUTO_INCREMENT,
    gerobak_id integer,
    item_id integer,
    gdetail_item_amount integer
);

CREATE TABLE herai_point(
    hpoint_id integer PRIMARY KEY AUTO_INCREMENT,
    hpoint_address varchar(250),
    hpoint_phonenumber varchar(20),
    hpoint_coordinate varchar(150)
);

CREATE TABLE herai_courier(
    hcourier_id integer PRIMARY KEY AUTO_INCREMENT,
    hcourier_name varchar(100),
    hcourier_phonenumber varchar(20),
    hpoint_id integer
);

CREATE TABLE pickup(
    pickup_id integer PRIMARY KEY AUTO_INCREMENT,
    gerobak_id integer,
    hpoint_id integer,
    pickup_schedule varchar(50),
    hcourier_id integer
);

INSERT INTO item(item_name, item_weight, item_details, item_category_id) VALUES 
    ('Bowl', 200, 'Mangkok Keramik, gr', 1),
    ('Plate', 156, 'Piring Keramik, gr', 1),
    ('Cup / Mug', 110, 'Gelas Keramik, gr', 1),    
    ('Tray', 200, 'Nampan Keramik, gr', 1),
    
    ('Plastic Bottle', 20, 'Botol Plastik', 2),
    ('Plastic Bottle', 18, 'Botol Plastik', 2),
    ('Plastic Bottle', 15, 'Botol Plastik', 2),
    ('Plastic Bottle', 11, 'Botol Plastik, gr', 2),
    ('Plastic Bottle', 13, 'Botol Plastik, gr', 2),
    ('Plastic Bottle', 17, 'Botol Plastik', 2),
    ('Plastic Bottle', 18, 'Botol Plastik', 2),
    ('Plastic Bottle', 15, 'Botol Plastik, gr', 2),
    ('Plastic Bottle', 16, 'Botol Plastik, gr', 2),
    ('Plastic Bottle', 11, 'Botol Plastik', 2),
    ('Plastic Bottle', 26, 'Botol Plastik, gr', 2),
    ('Plastic Bottle', 29, 'Botol Plastik, gr', 2),
    ('Tablespoon', 5, 'Sendok Makan Plastik', 2),
    ('Rice Spoon', 7, 'Sendok Nasi Plastik', 2),
    ('Tea Spoon', 3, 'Sendok Teh Plastik', 2),
    ('Fork', 7, 'Garpu Plastik', 2),
    ('Measuring Cup', 10, 'Gelas Ukur Plastik', 2),
    ('Food Container', 20, 'Kotak Makan Plastik', 2),
    ('Lunchbox', 25, 'Kotak Makan Siang Plastik', 2),
    ('Straw', 1, 'Sedotan Plastik,mm ', 2),
    ('Boba Straw', 9 , 'Sedotan Plastik Boba, mm', 2),
    ('Jar', 30, 'Toples Plastik', 2),
    ('Tray', 15, 'Nampan Plastik', 2),
    
    ('Can', 7, 'Gelas Kaleng', 3),
    ('Tray', 20, 'Nampan Aluminium', 3),
    ('Bowl', 25, 'Mangkok Aluminium', 3),
    ('Pan', 50, 'Panci Aluminium', 3),
    
    ('Bowl', 30, 'Mangkok Kaca', 4),
    ('Bottle', 50, 'Botol Kaca, ml', 4),
    ('Bottle', 150, 'Botol Kaca, ml', 4),
    ('Bottle', 200, 'Botol Kaca, ml', 4),
    ('Bottle', 250, 'Botol Kaca, ml', 4),
    ('Bottle', 460, 'Botol Kaca, ml', 4),
    ('Bottle', 600, 'Botol Kaca, ml', 4),
    ('Jar', 50, 'Toples Kaca', 4),
    ('Plate', 25, 'Piring Kaca', 4),
    
    ('Plate', 15, 'Piring Kayu', 5),
    ('Tablespoon', 8, 'Sendok Makan Kayu', 5),
    ('Tray', 19, 'Nampan Kayu', 5),
    ('Toothpick', 1, 'Tusuk Gigi', 5),
    ('Chopstick', 5, 'Sumpit Kayu', 5),
    
    ('Cardboard', 5, 'Kardus 39x26x22', 6),
    ('Cardboard', 5, 'Kardus 32x20x23', 6),
    ('Cardboard', 5, 'Kardus 60x40x30', 6),
    ('Corrugated Box', 5, 'Kotak Kardus', 6),
    ('Envelope', 1, 'Amplop Kertas', 6),
    ('Plate', 2, 'Piring Kertas', 6),
    ('Cup / Mug', 2, 'Gelas Kertas', 6),
    ('Magazine', 10, 'Majalah Kertas', 6),
    ('Book', 25, 'Buku', 6)
; 

INSERT INTO item_category(item_category_name) VALUES 
    ('Ceramic'), ('Plastic'), ('Aluminium / Metal'), ('Glass'), ('Wood'), ('Paper')
;
    
INSERT INTO herai_point(hpoint_address, hpoint_phonenumber, hpoint_coordinate) VALUES
    ('jl. Manggarai no.12, Baduy, Jakarta', 08928572912, 1432412315212323),
    ('jl. Bubutan Utara no.512A Ruko Jaya Utama, Surabaya', 082841928382, 1432412315212323),
    ('jl. Soekarno Hatta Barat no.4, Magelang', 082331859273, 1432412315212323)
;

INSERT INTO herai_courier(hcourier_name, hcourier_phonenumber, hpoint_id) VALUES
    ('Raffi Ahmad', 082910392842, 1),
    ('Cici Paramida', 081184928384, 2)
;

INSERT INTO user(username, user_fullname, user_password, user_email) VALUES
    ('rezky420', 'Rezky Andi', '123', 'rezz@gmail.com'),
    ('21chika21', 'Chipa Kamulang S', '321', 'chika@gmail.com')
;

INSERT INTO user_details(user_id, udetails_address, udetails_phonenumber) VALUES 
    (1, 'Apartemen Ciputra no. 21 blok FF', 081184928384),
    (2, 'Perumahan Ciputra blok F no. 69', 0811832128711),
    (2, 'jl. Pegangsaan Timur no.56', 0811832128711)
    
;

INSERT INTO gerobak(user_id, gerobak_weight, gerobak_price) VALUES 
    (1, 321, 56000),
    (1, 5523, 11000),
    (2, 100, 77000)
;

INSERT INTO gerobak_detail(gerobak_id, item_id, gdetail_item_amount) VALUES
    (1, 52, 3),
    (1, 22, 21),
    (1, 3, 500),
    (2, 44, 1242),
    (2, 10, 20),
    (3, 31, 700)
;

INSERT INTO pickup(gerobak_id, hpoint_id, pickup_schedule, hcourier_id) VALUES 
    (1, 1, '2 Mei 2022, 17:02:04', 1),
    (2, 2, '14 Agustus 2022, 09:44:20', 2),
    (1, 2, '29 Januari 2022, 12:10:01', 1)
;



SELECT * FROM item;

SELECT * FROM item_category;

SELECT * FROM herai_point;

SELECT * FROM herai_courier;

SELECT * FROM user;

SELECT * FROM user_details;

SELECT * FROM gerobak;

SELECT * FROM gerobak_detail;

SELECT * FROM pickup;


    
/* comment 

INSERT INTO item(item_name, item_weight, item_details) VALUES 
    ('teko', 120, 'barang teko'),
    ('gelas', 156, 'barang gelas'); 

*/