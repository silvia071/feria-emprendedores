package com.feria.servicios;

import com.feria.modelos.Venta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GestorVentasMockTest {

    @Test
    void deberiaCalcularVentasTotalesUsandoMockDeGestorFeria() {
        GestorFeria gestorMock = mock(GestorFeria.class);

        Venta venta1 = new Venta(
                "V001",
                "E001",
                "Mouse",
                2,
                1000,
                "2026-06-01"
        );

        Venta venta2 = new Venta(
                "V002",
                "E002",
                "Teclado",
                1,
                3000,
                "2026-06-01"
        );

        when(gestorMock.getVentas()).thenReturn(List.of(venta1, venta2));

        Reportes reportes = new Reportes();

        double total = reportes.calcularVentasTotales(gestorMock);

        assertEquals(5000, total);

        verify(gestorMock).getVentas();
    }
}
