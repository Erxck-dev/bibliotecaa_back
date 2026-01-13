package com.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.repositorio.LibrosRepositorio;

@SpringBootApplication
public class BibliotecaGrupo1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaGrupo1Application.class, args);
	}

	@Autowired
	private LibrosRepositorio repositorio;
	@Override
	public void run(String... args) throws Exception { 

	}

}
