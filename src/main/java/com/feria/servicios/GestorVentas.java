package com.feria.servicios;

import com.feria.modelos.Venta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorVentas {
    private List<Venta> ventas;

    public GestorVentas() {
        this.ventas = new ArrayList<>();
    }

    public void registrarVenta(Venta venta) {
        if (venta != null) {
            this.ventas.add(venta);
        }
    }

    public void procesarVentasPendientesYCobrar() {
        double totalRecaudado = 0;
        for (Venta venta : ventas) {
            if (venta.estaPendienteDePago()) {
                double monto = venta.calcularTotalConDescuento();
                totalRecaudado += monto;
                venta.registrarPago();
                System.out.println("Cobrada venta " + venta.getIdVenta() + " por $" + monto);
            }
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
    }

    public List<Venta> getVentas() {
        return Collections.unmodifiableList(ventas);
    }
}
