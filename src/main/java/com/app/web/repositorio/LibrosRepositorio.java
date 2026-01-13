package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Libros;

@Repository
public interface LibrosRepositorio extends JpaRepository<Libros, Long> {

    // BUSCAR POR TITULO
    List<Libros> findByTituloContainingIgnoreCase(String titulo);
}
