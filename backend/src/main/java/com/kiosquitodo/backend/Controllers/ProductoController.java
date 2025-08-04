package com.kiosquitodo.backend.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiosquitodo.backend.Models.ProductoRequestDTO;
import com.kiosquitodo.backend.Models.ProductoResponseDTO;
import com.kiosquitodo.backend.Services.ProductoServicio;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServicio servicio;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Boolean> createProduct(@RequestBody ProductoRequestDTO request) {
        Boolean result = servicio.agregarProducto(request);
        if (!result) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    // READ
    @GetMapping("/read")
    public ResponseEntity<ProductoResponseDTO> readProduct(@RequestParam String nombre) {
        try {
            ProductoResponseDTO dto = servicio.obtenerProductoPorNombre(nombre);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<ProductoResponseDTO> updateProduct(@RequestBody ProductoRequestDTO request) {
        try {
            ProductoResponseDTO actualizado = servicio.actualizarProductoPorNombre(request);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String nombre) {
        Boolean respuesta = servicio.eliminarProducto(nombre);
        if (!respuesta) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
