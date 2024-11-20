create database gestion_biblioteca;

use gestion_biblioteca;

CREATE TABLE bibliotecarios (
    username VARCHAR(10),
    pass VARCHAR(20)
);

CREATE TABLE usuarios (
    id VARCHAR(3),
    nombre VARCHAR(100),
    email VARCHAR(30)
);

CREATE TABLE libros (
    id VARCHAR(3),
    titulo VARCHAR(50),
    autor VARCHAR(30),
    prestado BOOLEAN
);
 