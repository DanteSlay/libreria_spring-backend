package com.javier.libreria_springbackend.models.mapper;

import com.javier.libreria_springbackend.models.dto.AutorDto;
import com.javier.libreria_springbackend.models.dto.LibroDto;
import com.javier.libreria_springbackend.models.entity.Libro;
import com.javier.libreria_springbackend.models.service.AutorService;

import java.util.Optional;

public class LibroMapper {

    public static LibroDto toDto(Libro libro) throws NullPointerException {
        LibroDto dto = new LibroDto();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutorID(libro.getAutor().getId());

        return dto;
    }

    public static Libro toEntity(LibroDto dto, AutorService autorService) {
        Optional<AutorDto> autor = autorService.findById(dto.getAutorID());

            if (autor.isPresent()) {
                Libro libro = new Libro(dto.getTitulo());
                libro.setAutor(AutorMapper.toEntity(autor.get()));

                if (dto.getId() != null) {
                    libro.setId(dto.getId());
                }

                return libro;
            }
        return null;
    }
}
