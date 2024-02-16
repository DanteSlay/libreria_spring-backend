package com.javier.libreria_springbackend.models.service;

import com.javier.libreria_springbackend.models.dto.LibroDto;
import com.javier.libreria_springbackend.models.entity.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroCRUDServiceImpl {
    List<LibroDto> findAll();

    LibroDto save(LibroDto libroDto);

    Optional<LibroDto> findById(Long id);

    void delete(Long id);

    boolean existById(Long id);
}
