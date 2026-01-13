package com.app.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.modelos.Usuarios;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long>{
	public Usuarios findByEmail(String email);

}
