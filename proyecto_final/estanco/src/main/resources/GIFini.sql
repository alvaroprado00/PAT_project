DROP TABLE IF EXISTS "GIFS";

CREATE TABLE "GIFS"(

id INTEGER IDENTITY(1,1),
nombre VARCHAR(25),
contenido VARCHAR(300));


INSERT INTO "GIFS" (nombre, contenido) VALUES
('gifEjemplo', 'https://m.gifmania.com/Gif-Animados-Objetos/Imagenes-Tabaco/Cigarros/Cigarro-Amarillo-72337.gif');