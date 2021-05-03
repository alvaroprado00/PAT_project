CREATE TABLE IF NOT EXISTS GIFS (

id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(25),
contenido VARCHAR(300));

CREATE TABLE IF NOT EXISTS TABACOLIAR (
id INT AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(50),
marca VARCHAR(50),
descripcion VARCHAR(200),
imagen VARCHAR(200),
precio FLOAT,
gramos INT);

CREATE TABLE IF NOT EXISTS TABACOINDUSTRIAL (
id INT AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(50),
marca VARCHAR(50),
descripcion VARCHAR(200),
imagen VARCHAR(200),
precio FLOAT,
cigarrillos INT);

CREATE TABLE IF NOT EXISTS users(

id_user INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(30),
last_name VARCHAR(30),
user_name VARCHAR(30),
password VARCHAR(50),
email VARCHAR(50)

);

CREATE TABLE IF NOT EXISTS usersInfoPurchase(

id_user INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(30),
last_name VARCHAR(30),
email VARCHAR(30),
address VARCHAR(50),
country VARCHAR(50),
zip VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usersPurchases(
id INT AUTO_INCREMENT PRIMARY KEY,
firstName VARCHAR(50),
lastName VARCHAR(50),
email VARCHAR(50),
address VARCHAR(50),
country VARCHAR(50),
userName VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS shopping_carts(

id_shopping_cart INT AUTO_INCREMENT PRIMARY KEY,
number_purchases INT,
id_user INT REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS purchases(

id_purchase VARCHAR(50) PRIMARY KEY,
date_purchase VARCHAR(50),
id_shopping_cart INT REFERENCES shopping_carts(id_shopping_cart)
);


CREATE TABLE IF NOT EXISTS lineas_compra(

codigo INT AUTO_INCREMENT PRIMARY KEY,
units INT,
tipo_tabaco VARCHAR(50),
id_purchase INT REFERENCES purchases(id_purchase)
);

CREATE TABLE IF NOT EXISTS coments(

coment_token VARCHAR(10) PRIMARY KEY,
coment_content VARCHAR(300),
positive_experience BOOLEAN,
id_user INT REFERENCES users(id_user)
);
