package com.javier.libreria_springbackend.models.dao;

import com.javier.libreria_springbackend.models.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroDao extends JpaRepository<Libro, Long> {
}
