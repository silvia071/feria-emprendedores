package com.feria.modelos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void deberiaDetectarStockBajo() {

        Producto producto =
                new Producto(
                        "Mouse",
                        1000,
                        4,
                        "tecnologia",
                        "E001"
                );

        assertTrue(producto.tieneStockBajo());
    }

    @Test
    void noDebeDescontarMasStockDelDisponible() {

        Producto producto =
                new Producto(
                        "Mouse",
                        1000,
                        5,
                        "tecnologia",
                        "E001"
                );

        producto.descontarStock(10);

        assertEquals(5, producto.getStock());
    }

    @Test
    void productoPremium() {

        Producto producto =
            new Producto(
                    "Notebook",
                    15000,
                    5,
                    "tecnologia",
                    "E001"
            );

        assertTrue(producto.esPremium());
    }
}