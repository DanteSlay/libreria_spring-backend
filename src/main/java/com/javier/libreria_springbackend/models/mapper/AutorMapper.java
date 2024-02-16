package com.javier.libreria_springbackend.models.mapper;

import com.javier.libreria_springbackend.models.dto.AutorDto;
import com.javier.libreria_springbackend.models.entity.Autor;

public class AutorMapper {

    public static AutorDto toDto(Autor autor) {
        AutorDto dto = new AutorDto();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());

        return dto;
    }

    public static Autor toEntity(AutorDto dto) {
            Autor autor = new Autor();
        if (dto.getId() != null) {
            autor.setId(dto.getId());
            autor.setNombre(dto.getNombre());

            return autor;
        }

        autor.setNombre(dto.getNombre());
        return autor;

    }
}
