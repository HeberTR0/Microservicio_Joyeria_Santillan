-- Insertar usuario ADMINISTRADOR
-- Username: admin, Password: admin123
INSERT IGNORE INTO usuarios (username, password, nombre, rol, activo)
VALUES ('admin', '$2a$10$B5beA.1Yr0Dg32unXx27suyPm40uyEFrNXEzOMUmMxhKErsk6BW5G', 'Administrador', 'ADMINISTRADOR', true);

-- Insertar usuario EMPLEADO
-- Username: empleado, Password: empleado123
INSERT IGNORE INTO usuarios (username, password, nombre, rol, activo)
VALUES ('empleado', '$2a$10$6HTxbtQMknanrHkF6guqXuQZG56YBKn6cyliofjpTxm8/KfMqzEz6', 'Empleado', 'EMPLEADO', true);