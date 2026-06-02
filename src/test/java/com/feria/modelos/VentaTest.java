package com.feria.modelos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VentaTest {

    @Test
    void calcularSubtotalCorrectamente() {

        Venta venta =
                new Venta(
                        "V001",
                        "E001",
                        "Mouse",
                        2,
                        1000,
                        "2026-06-01"
                );

        assertEquals(
                2000,
                venta.calcularSubtotal()
        );
    }

    @Test
    void aplicarDescuentoPorCantidad() {

        Venta venta =
                new Venta(
                        "V001",
                        "E001",
                        "Mouse",
                        15,
                        1000,
                        "2026-06-01"
                );

        assertEquals(
                12825,
                venta.calcularTotalConDescuento()
        );
    }
}