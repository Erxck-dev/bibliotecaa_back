INSERT INTO rol (nombre) VALUES ('ROLE_USER') ON CONFLICT DO NOTHING;
INSERT INTO rol (nombre) VALUES ('ROLE_ADMIN') ON CONFLICT DO NOTHING;

-- 2. Convertir tu usuario en ADMIN
-- Reemplaza 'tu_correo@gmail.com' por el correo con el que hiciste el registro en la web
INSERT INTO usuarios_roles (usuario_id, rol_id)
SELECT u.id, r.id 
FROM usuarios u, rol r 
WHERE u.email = 'erick.valladares6223@utc.edu.ec' 
AND r.nombre = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;
