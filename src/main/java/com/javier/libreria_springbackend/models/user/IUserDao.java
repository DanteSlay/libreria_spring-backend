package com.javier.libreria_springbackend.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
