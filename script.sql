-- use gestion_biblioteca;

DROP PROCEDURE IF EXISTS sp_insertar_usuarios;

delimiter $$
CREATE PROCEDURE sp_insertar_usuarios()
BEGIN
	DECLARE usuario_count INT;
    -- Verificamos si la tabla está vacía
    SELECT COUNT(*) INTO usuario_count FROM usuarios;
    
    IF usuario_count = 0 THEN
        -- Insertamos los 10 usuarios solo si la tabla está vacía
        INSERT INTO usuarios (nombre, email) VALUES
            ('Ana García', 'ana.garcia@email.com'),
            ('Carlos Rodríguez', 'carlos.rodriguez@email.com'),
            ('María López', 'maria.lopez@email.com'),
            ('Juan Martínez', 'juan.martinez@email.com'),
            ('Laura Sánchez', 'laura.sanchez@email.com'),
            ('Roberto Fernández', 'roberto.fernandez@email.com'),
            ('Patricia Torres', 'patricia.torres@email.com'),
            ('Miguel Ramírez', 'miguel.ramirez@email.com'),
            ('Isabel Castro', 'isabel.castro@email.com'),
            ('Diego Morales', 'diego.morales@email.com');
    END IF;
    select * from usuarios;
END$$
delimiter ;

-- Para ejecutar el stored procedure:
-- CALL sp_insertar_usuarios();


DROP PROCEDURE IF EXISTS sp_insertar_bibliotecarios;

delimiter $$
CREATE PROCEDURE sp_insertar_bibliotecarios()
BEGIN
	DECLARE biblio_count INT;
    -- Verificamos si la tabla está vacía
    SELECT COUNT(*) INTO biblio_count FROM bibliotecarios;
    
    IF biblio_count = 0 THEN
		INSERT INTO bibliotecarios (username, pass) VALUES
			('biblio_ana', 'ana123secure'),
			('biblio_juan', 'juan456secure'),
			('biblio_maria', 'maria789secure'),
			('biblio_carlos', 'carlos101secure'),
			('biblio_laura', 'laura202secure');
	end if;
	select * from bibliotecarios;
END$$
delimiter ;

-- Para ejecutar el stored procedure:
-- CALL sp_insertar_bibliotecarios();

DROP PROCEDURE IF EXISTS sp_insertar_libros;

delimiter $$
CREATE PROCEDURE sp_insertar_libros()
BEGIN
	DECLARE libros_count INT;
    -- Verificamos si la tabla está vacía
    SELECT COUNT(*) INTO libros_count FROM libros;
    
    IF libros_count = 0 THEN
		INSERT INTO libros (titulo, autor, isbn, disponible) VALUES
			('Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', true),
			('Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8420412146', true),
			('1984', 'George Orwell', '978-0451524935', false),
			('El principito', 'Antoine de Saint-Exupéry', '978-0156012195', true),
			('Rayuela', 'Julio Cortázar', '978-8437604572', false),
			('La casa de los espíritus', 'Isabel Allende', '978-0525433477', true),
			('El laberinto de la soledad', 'Octavio Paz', '978-9681603011', true),
			('Pedro Páramo', 'Juan Rulfo', '978-0802133908', false),
			('La sombra del viento', 'Carlos Ruiz Zafón', '978-0143034902', true),
			('Los detectives salvajes', 'Roberto Bolaño', '978-0312427481', true);
			
	end if;
	SELECT * FROM libros ORDER BY titulo;
END$$
delimiter ;

-- Para ejecutar el stored procedure:
-- CALL sp_insertar_libros();