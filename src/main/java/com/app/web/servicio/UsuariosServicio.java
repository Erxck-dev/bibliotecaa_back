package com.app.web.servicio;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.web.dto.UsuariosRegistroDTO;
import com.app.web.modelos.Usuarios;

public interface UsuariosServicio extends UserDetailsService{
	
	public Usuarios guardar(UsuariosRegistroDTO registroDTO);
	
	

}
