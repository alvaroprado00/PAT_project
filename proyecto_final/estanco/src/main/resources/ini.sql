

CREATE TABLE IF NOT EXISTS GIFS (

id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(25),
contenido VARCHAR(300));

INSERT INTO GIFS (nombre, contenido) VALUES
('gifEjemplo', 'https://m.gifmania.com/Gif-Animados-Objetos/Imagenes-Tabaco/Cigarros/Cigarro-Amarillo-72337.gif');

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