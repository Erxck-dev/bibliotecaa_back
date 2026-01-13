package com.app.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.web.modelos.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
