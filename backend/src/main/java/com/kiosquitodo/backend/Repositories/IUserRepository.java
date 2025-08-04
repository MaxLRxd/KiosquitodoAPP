package com.kiosquitodo.backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosquitodo.backend.Models.Producto;

public interface IUserRepository extends JpaRepository<Producto, Long>{
    Optional<Producto> findByName(String nombre);
    Optional<Producto> findByPrice(double precio);
    boolean existsByName(String nombre);
}
