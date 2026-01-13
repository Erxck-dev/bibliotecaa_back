package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.web.entidad.Libros;
import com.app.web.servicio.LibrosServicio;

@Controller
@RequestMapping("/libros")
public class LibrosControlador {

    @Autowired
    private LibrosServicio servicio;

    // LISTAR
    @GetMapping
    public String listarLibros(Model modelo) {
        modelo.addAttribute("libros", servicio.ListarTodosLosLibros());
        return "libros";
    }

    // CREAR
    @GetMapping("/nuevo")
    public String mostrarCrearLibrosFormulario(Model modelo) {
        modelo.addAttribute("libros", new Libros());
        return "crear_libros";
    }

    // GUARDAR
    @PostMapping
    public String guardarLibros(@ModelAttribute("libros") Libros libros) {
        servicio.guardarLibros(libros);
        return "redirect:/libros";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("libros", servicio.obtenerLibrosPorId(id));
        return "editar_libros";
    }

    // ACTUALIZAR
    @PostMapping("/{id}")
    public String actualizarLibros(
            @PathVariable Long id,
            @ModelAttribute("libros") Libros libros) {

        Libros existente = servicio.obtenerLibrosPorId(id);
        existente.setTitulo(libros.getTitulo());
        existente.setAutor(libros.getAutor());
        existente.setIsbn(libros.getIsbn());
        existente.setCopias(libros.getCopias());

        servicio.ActualizarLibros(existente);
        return "redirect:/libros";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminarLibros(@PathVariable Long id) {
        servicio.eliminarLibros(id);
        return "redirect:/libros";
    }
}
