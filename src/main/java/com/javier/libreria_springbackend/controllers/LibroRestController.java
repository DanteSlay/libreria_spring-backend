package com.javier.libreria_springbackend.controllers;

import com.javier.libreria_springbackend.exception.BadRequestException;
import com.javier.libreria_springbackend.exception.ResourceNotFoundException;
import com.javier.libreria_springbackend.models.dto.LibroDto;
import com.javier.libreria_springbackend.models.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/libro")
public class LibroRestController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<LibroDto> libros = libroService.findAll();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<LibroDto> libroDto = libroService.findById(id);

        if (libroDto.isEmpty()) {
            throw new ResourceNotFoundException("libro", "id", id);
        } else {
            return new ResponseEntity<>(libroDto.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            Optional<LibroDto> libroDelete = libroService.findById(id);
            libroService.delete(id);
            return new ResponseEntity<>(libroDelete, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid LibroDto libroDto) {

        try {
            libroService.save(libroDto);
            return new ResponseEntity<>(libroDto, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid LibroDto libroDto, @PathVariable Long id) {

        if (!libroService.existById(id)) {
            throw new ResourceNotFoundException("libro", "id", id);
        }

        try {
            libroDto.setId(id);
            libroService.save(libroDto);
            return new ResponseEntity<>(libroDto, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Map<String, Object> actualizaciones, @PathVariable Long id) {
        Optional<LibroDto> optionalLibroDto = libroService.findById(id);
        if (optionalLibroDto.isEmpty()) {
            throw new ResourceNotFoundException("libro", "id", id);
        }

        LibroDto libroDto = optionalLibroDto.get();
        libroDto.setId(id);
        if (actualizaciones.containsKey("titulo")) {
            libroDto.setTitulo((String) actualizaciones.get("titulo"));
        }
        if (actualizaciones.containsKey("autorID")) {
            libroDto.setAutorID(((Integer) actualizaciones.get("autorID")).longValue());
        }

        if (LibroDto.validar(libroDto)) {
                libroService.save(libroDto);
                return new ResponseEntity<>(libroDto, HttpStatus.OK);
        } else {
            throw new BadRequestException("Alguno de los campos no es correcto");
        }
    }
}