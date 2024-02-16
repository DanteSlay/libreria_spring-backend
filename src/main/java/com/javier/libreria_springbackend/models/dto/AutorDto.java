package com.javier.libreria_springbackend.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AutorDto implements Serializable {
    private Long id;

    @NotBlank
    private String nombre;

    public AutorDto() {
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
}
