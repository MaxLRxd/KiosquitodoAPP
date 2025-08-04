package com.kiosquitodo.backend.Models;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private String nombre;
    private int cantidad;
    private Double precio;
    // Constructor que recibe la entidad
    public ProductoResponseDTO(Producto producto) {
        this.nombre = producto.getNombre();
        this.cantidad = producto.getCantidad();
        this.precio = producto.getPrecio();
    }
}
