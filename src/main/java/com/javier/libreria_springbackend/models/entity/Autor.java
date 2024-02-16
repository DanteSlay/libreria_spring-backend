package com.javier.libreria_springbackend.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {
        libros = new ArrayList<>();
    }

    public Autor(String nombre) {
        this();
        this.nombre = nombre;
    }

    public void addLibro(Libro libro) {
        libros.add(libro);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
