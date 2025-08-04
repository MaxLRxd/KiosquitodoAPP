package com.kiosquitodo.backend.Services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.kiosquitodo.backend.Models.Producto;
import com.kiosquitodo.backend.Models.ProductoRequestDTO;
import com.kiosquitodo.backend.Models.ProductoResponseDTO;
import com.kiosquitodo.backend.Repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final IUserRepository repositorio;

    // AGREGAR PRODUCTO
    public Boolean agregarProducto(ProductoRequestDTO dto) {
        if (repositorio.existsByNombre(dto.getNombre())) {
            return false;
        }

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setCantidad(dto.getCantidad());
        producto.setPrecio(dto.getPrecio());

        repositorio.save(producto);
        return true;
    }

    // ELIMINAR PRODUCTO
    public Boolean eliminarProducto(String nombre) {
        Optional<Producto> producto = repositorio.findByNombre(nombre);
        if (producto.isEmpty()) {
            return false;
        }

        repositorio.delete(producto.get());
        return true;
    }

    // OBTENER PRODUCTO
    public ProductoResponseDTO obtenerProductoPorNombre(String nombre) {
        Producto producto = repositorio.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return new ProductoResponseDTO(producto);
    }

    // ACTUALIZAR PRODUCTO
    public ProductoResponseDTO actualizarProductoPorNombre(ProductoRequestDTO dto) {
        Producto producto = repositorio.findByNombre(dto.getNombre())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        producto.setCantidad(dto.getCantidad());
        producto.setPrecio(dto.getPrecio());

        Producto actualizado = repositorio.save(producto);
        return new ProductoResponseDTO(actualizado);
    }
}
