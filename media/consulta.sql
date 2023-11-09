/*DROP TABLE IF EXISTS pruebaNominas.Nominas;
DROP TABLE IF EXISTS pruebaNominas.Empleados;
DROP DATABASE IF EXISTS pruebaNominas;

CREATE DATABASE IF NOT EXISTS pruebaNominas;

-- Usar la base de datos recién creada
USE pruebaNominas;

-- Crear la tabla Empleado
CREATE TABLE Empleados (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255),
    sexo CHAR(1),
    categoria INT,
    anyos DOUBLE
);

-- Crear la tabla Nomina con la clave foránea empleado_dni
CREATE TABLE Nominas (
    empleado_dni VARCHAR(20),
    nomina DOUBLE,
    FOREIGN KEY (empleado_dni) REFERENCES Empleados(dni)
);


-- Usar la base de datos
USE pruebaNominas;*/

-- Insertar datos en la tabla Empleado
/*INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos)
VALUES
    ('32000032G', 'James Cosling', 'M', 4, 7),
    ('32000031R', 'Ada Lovelace', 'F', 1, 0);*/
    
SELECT nomina FROM nominas WHERE empleado_dni = '32000031R'