package com.app.web.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.dto.UsuariosRegistroDTO;
import com.app.web.servicio.UsuariosServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuariosControlador {
	
	private UsuariosServicio usuariosServicio;

	public RegistroUsuariosControlador(UsuariosServicio usuariosServicio) {
		super();
		this.usuariosServicio = usuariosServicio;
	}

	@ModelAttribute("usuario")
	public UsuariosRegistroDTO retornarNuevoUsuariosRegistroDTO() {
		return new UsuariosRegistroDTO();
	}
	
	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}

	
	@PostMapping
	public String registrarCuentaDeUsuarios(@ModelAttribute("usuario")UsuariosRegistroDTO registroDTO) {
		usuariosServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
