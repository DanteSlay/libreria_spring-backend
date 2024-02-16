-- -- Insertar roles
-- INSERT INTO roles (nombre) VALUES ('ROLE_USER');
-- INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
-- INSERT INTO roles (nombre) VALUES ('ROLE_MANAGER');
-- INSERT INTO roles (nombre) VALUES ('ROLE_SUPER_ADMIN');
--
-- Insertar usuarios
INSERT INTO users (nombre, username, password, role) VALUES ('Chanchito1', 'Chanchito1','$2y$04$bkWY11ZzxVFxvb/OUZeO0uF8W5nvUnt/0KYTQrdWBtl0q2g6hZI2G', 'USER');
INSERT INTO users (nombre, username, password, role) VALUES ('Chanchito2', 'Chanchito2', '$2y$04$yPLvnBpoZl4W0lwVc6pnVemJ.PcRYRILh325D2F.QSnrYI./iEUYq', 'ADMIN');
INSERT INTO users (nombre, username, password, role) VALUES ('Chanchito3', 'Chanchito3', '$2y$04$YSBd4PHAon4yAO2J43BFQeI7PRx5biroVT4o0BGTPZZ9RRamp2/8S', 'USER');
INSERT INTO users (nombre, username, password, role) VALUES ('Chanchito4', 'Chanchito4', '$2y$04$YSBd4PHAon4yAO2J43BFQeI7PRx5biroVT4o0BGTPZZ9RRamp2/8S', 'USER');

-- -- Asignar roles a usuarios
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito1'), (SELECT id FROM roles WHERE nombre = 'ROLE_USER'));
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito4'), (SELECT id FROM roles WHERE nombre = 'ROLE_USER'));
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito3'), (SELECT id FROM roles WHERE nombre = 'ROLE_MANAGER'));
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito2'), (SELECT id FROM roles WHERE nombre = 'ROLE_USER'));
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito2'), (SELECT id FROM roles WHERE nombre = 'ROLE_ADMIN'));
-- INSERT INTO users_roles (user_id, roles_id) VALUES ((SELECT id FROM users WHERE username = 'Chanchito2'), (SELECT id FROM roles WHERE nombre = 'ROLE_SUPER_ADMIN'));


insert into autores (nombre) VALUES ('Miguel Cervantes');
insert into autores (nombre) VALUES ('George R. Martin');

insert into libros (titulo, autor_id) VALUES ('Quijote', 1);
insert into libros (titulo, autor_id) VALUES ('Juego de Tronos', 2);