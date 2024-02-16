package com.javier.libreria_springbackend.models.service;

import com.javier.libreria_springbackend.models.dto.AutorDto;
import com.javier.libreria_springbackend.models.entity.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorCRUDServiceImpl {
    List<Autor> findAll();

    Autor save(Autor autor);

    Optional<AutorDto> findById(Long id);

    void delete(Long id);
}
