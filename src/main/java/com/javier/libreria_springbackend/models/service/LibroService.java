package com.javier.libreria_springbackend.models.service;

import com.javier.libreria_springbackend.models.dao.ILibroDao;
import com.javier.libreria_springbackend.models.dto.LibroDto;
import com.javier.libreria_springbackend.models.entity.Libro;
import com.javier.libreria_springbackend.models.mapper.LibroMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class LibroService implements ILibroCRUDServiceImpl {

    private final ILibroDao libroDao;

    private final AutorService autorService;
    
    @Override
    @Transactional(readOnly = true)
    public List<LibroDto> findAll() {
        List<Libro> libros = libroDao.findAll();

        List<LibroDto> dtos = new ArrayList<>();
        for (Libro l : libros) {
            dtos.add(LibroMapper.toDto(l));
        }

        return dtos;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasRole('ADMIN')")
    public LibroDto save(LibroDto libroDto) {
        Libro libro = LibroMapper.toEntity(libroDto, autorService);
        libroDao.save(libro);
        return libroDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LibroDto> findById(Long id) {
        Libro libro = libroDao.findById(id).orElse(null);
        if (libro != null) {
            return Optional.of(LibroMapper.toDto(libro));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<LibroDto> libroDtoOptional = findById(id);
        if (libroDtoOptional.isPresent()) {
        libroDao.deleteById(id);

        }else {
            throw new NoSuchElementException("El libro ID: " + id + " no existe.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return libroDao.existsById(id);
    }
}
