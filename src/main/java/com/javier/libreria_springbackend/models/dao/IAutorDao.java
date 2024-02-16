package com.javier.libreria_springbackend.models.dao;

import com.javier.libreria_springbackend.models.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorDao extends JpaRepository<Autor, Long> {
}
