package com.app.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.web.entidad.Libros;
import com.app.web.repositorio.LibrosRepositorio;

@Service
public class LibrosServicioImpl implements LibrosServicio {

    @Autowired
    private LibrosRepositorio repositorio;
    @Autowired
    private EmailServicio emailServicio;

    // ADMIN

    @Override
    public List<Libros> ListarTodosLosLibros() {
        return repositorio.findAll();
    }

    @Override
    public Libros guardarLibros(Libros libros) {
        return repositorio.save(libros);
    }

    @Override
    public Libros obtenerLibrosPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Libros ActualizarLibros(Libros libros) {
        return repositorio.save(libros);
    }

    @Override
    public void eliminarLibros(Long id) {
        repositorio.deleteById(id);
    }

    // USUARIO

    @Override
    public List<Libros> buscarPorTitulo(String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public boolean reservarLibro(Long id,String emailUsuario) {

        Libros libro = repositorio.findById(id).orElse(null);

        if (libro == null || libro.getCopias() <= 0) {
            return false;
        }

        libro.setCopias(libro.getCopias() - 1);
        repositorio.save(libro);

        emailServicio.enviarCorreoReserva(
                "erick.valladares6223@utc.edu.ec",
                emailUsuario,
                libro.getTitulo()
        );

        return true;
    }

}
