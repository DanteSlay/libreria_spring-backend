package com.javier.libreria_springbackend.models.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

public class LibroDto implements Serializable {
    private Long id;
    @NotBlank(message = "no puede estar vacio")
    @Size(min = 3, max = 100, message = "El tamaño debe ser entre 3 y 100")
    private String titulo;
    @NotNull(message = "no puede estar vacio")
    private Long autorID;

    public LibroDto() {
    }

    public static boolean validar(LibroDto libroDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LibroDto>> invalidaciones = validator.validate(libroDto);

        if (!invalidaciones.isEmpty()) {
            for (ConstraintViolation<LibroDto> violation : invalidaciones) {
                System.out.println("Campo: " + violation.getPropertyPath());
                System.out.println("Mensaje: " + violation.getMessage());
            }
            return false;
        }

        return true;
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

    public Long getAutorID() {
        return autorID;
    }

    public void setAutorID(Long autorID) {
        this.autorID = autorID;
    }
}
