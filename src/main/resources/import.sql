INSERT INTO usuarios_roles (usuario_id, rol_id)
SELECT u.id, r.id 
FROM usuarios u, rol r 
WHERE u.email = 'erick.valladares6223@utc.edu.ec' 
AND r.nombre = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;
