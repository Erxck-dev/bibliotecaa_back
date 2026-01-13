package com.app.web.controlador;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.web.servicio.LibrosServicio;

@Controller
@RequestMapping("/usuario")
public class LibrosUsuarioControlador {

    private final LibrosServicio servicio;

    public LibrosUsuarioControlador(LibrosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/libros")
    public String verLibros(Model model) {
        model.addAttribute("libros", servicio.ListarTodosLosLibros());
        return "libros_usuario";
    }

    @GetMapping("/libros/buscar")
    public String buscarLibros(@RequestParam String titulo, Model model) {
        model.addAttribute("libros", servicio.buscarPorTitulo(titulo));
        return "libros_usuario";
    }

    @PostMapping("/libros/reservar/{id}")
    public String reservarLibro(
            @PathVariable Long id,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        String emailUsuario = principal.getName();

        boolean reservado = servicio.reservarLibro(
                id,
                emailUsuario
        );

        if (reservado) {
            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Libro reservado correctamente"
            );
        } else {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "No se pudo reservar el libro"
            );
        }

        return "redirect:/usuario/libros";
    }
}