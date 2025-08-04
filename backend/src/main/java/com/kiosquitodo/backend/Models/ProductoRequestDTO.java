package com.kiosquitodo.backend.Models;

import lombok.Data;

@Data
public class ProductoRequestDTO {
    private String nombre;
    private int cantidad;
    private Double precio;
}
