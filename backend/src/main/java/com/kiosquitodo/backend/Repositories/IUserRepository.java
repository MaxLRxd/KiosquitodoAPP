package com.kiosquitodo.backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosquitodo.backend.Models.Producto;

public interface IUserRepository extends JpaRepository<Producto, Long>{
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findByPrecio(double precio);
    Optional<Producto> findByCantidad(int cantidad);
    boolean existsByNombre(String nombre);
}
