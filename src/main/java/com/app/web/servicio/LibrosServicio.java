package com.app.web.servicio;

import java.util.List;
import com.app.web.entidad.Libros;

public interface LibrosServicio {

    // ADMIN
    List<Libros> ListarTodosLosLibros();

    Libros guardarLibros(Libros libros);

    Libros obtenerLibrosPorId(Long id);

    Libros ActualizarLibros(Libros libros);

    void eliminarLibros(Long id);

    // USER
    List<Libros> buscarPorTitulo(String titulo);

    boolean reservarLibro(Long id, String emailUsuario);


}
