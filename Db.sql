create database notas;

use notas;

CREATE TABLE estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    documento_identidad VARCHAR(50),
    fecha_nacimiento DATE
);

CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT
);

CREATE TABLE notas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT,
    curso_id INT,
    nota DECIMAL(5,2),
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE certificados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT,
    fecha_emision DATE,
    contenido TEXT,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id)
);
