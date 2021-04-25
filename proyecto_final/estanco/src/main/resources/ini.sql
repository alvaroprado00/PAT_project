CREATE TABLE IF NOT EXISTS GIFS (

id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(25),
contenido VARCHAR(300));

INSERT INTO GIFS (nombre, contenido) VALUES
('gifEjemplo', 'https://m.gifmania.com/Gif-Animados-Objetos/Imagenes-Tabaco/Cigarros/Cigarro-Amarillo-72337.gif');

CREATE TABLE IF NOT EXISTS TABACOLIAR (
id INT AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(50),
marca VARCHAR(50),
descripcion VARCHAR(200),
imagen VARCHAR(200),
precio FLOAT,
gramos INT);

INSERT INTO TABACOLIAR(codigo,marca,descripcion,imagen,precio,gramos) VALUES
('asdf01','Marlboro','Bolsa con tabaco para liar/entubar','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2015/12/26/19/53486855_070836.jpg',5.25,30),
('asdf02','Camel','Bolsa con tabaco para liar/entubar','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2011/07/03/27775050.jpg',5.00,30),
('asdf03','Chesterfield','Bolsa con tabaco para liar/entubar','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2013/02/19/35873891.jpg',5.00,30),
('asdf04','Flandria-Sauvag','Bolsa con tabaco para liar/entubar','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2015/12/26/19/53486743_070156.jpg',5.00,30),
('asdf05','Manitou','Bolsa con tabaco para liar/entubar','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2016/11/17/20/66772906.jpg',5.00,30);


CREATE TABLE IF NOT EXISTS TABACOINDUSTRIAL (
id INT AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(50),
marca VARCHAR(50),
descripcion VARCHAR(200),
imagen VARCHAR(200),
precio FLOAT,
cigarrillos INT);

INSERT INTO TABACOINDUSTRIAL(codigo,marca,descripcion,imagen,precio,cigarrillos) VALUES
('cwecw32e','Marlboro','Cajetilla de tabaco dura con cigarrillos prefabricados','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2015/07/26/19/50481579.jpg',5.00,20),
('dewfwec332s','Camel','Cajetilla de tabaco dura con cigarrillos prefabricados','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2012/03/06/30785864.jpg',5.00,20),
('dew23s2s','Chesterfield','Cajetilla de tabaco dura con cigarrillos prefabricados','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2016/05/24/10/57031356.jpg',5.00,20),
('3fw34dsa','L&M','Cajetilla de tabaco dura con cigarrillos prefabricados','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2016/06/29/13/57747354.jpg',5.00,20),
('rr332s4','Ducados','Cajetilla de tabaco dura con cigarrillos prefabricados','https://cloud10.todocoleccion.online/coleccionismo-tabaco/tc/2014/11/22/21/46372464.jpg',5.00,20);

CREATE TABLE IF NOT EXISTS users(

id_user INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(30),
last_name VARCHAR(30),
user_name VARCHAR(30),
password VARCHAR(50),
email VARCHAR(50)

);

CREATE TABLE IF NOT EXISTS shopping_carts(

id_shopping_cart INT AUTO_INCREMENT PRIMARY KEY,
number_purchases INT,
id_user INT REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS purchases(

id_purchase VARCHAR(50) PRIMARY KEY,
date_purchase VARCHAR(50),
id_shopping_cart INT REFERENCES shopping_carts
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