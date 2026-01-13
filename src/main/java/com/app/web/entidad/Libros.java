package com.app.web.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "copias", nullable = false)
    private Integer copias;


    public Libros() {
    }


    public Libros(Long id, String titulo, String autor, String isbn, Integer copias) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.copias = copias;
    }


    public Libros(String titulo, String autor, String isbn, Integer copias) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.copias = copias;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getCopias() {
        return copias;
    }

    public void setCopias(Integer copias) {
        this.copias = copias;
    }

    @Override
    public String toString() {
        return "Libros [id=" + id +
               ", titulo=" + titulo +
               ", autor=" + autor +
               ", isbn=" + isbn +
               ", copias=" + copias + "]";
    }
}
