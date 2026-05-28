package com.feria.servicios;

import com.feria.modelos.Emprendedor;
import com.feria.modelos.Venta;

public class Reportes {

  public String generarReportePorCategoria(GestorFeria gestor, String categoria) {
    StringBuilder reporte = new StringBuilder();

    reporte.append("=== REPORTE DE EMPRENDEDORES - CATEGORIA: ")
        .append(categoria)
        .append(" ===\n");

    for (Emprendedor emprendedor : gestor.getEmprendedores()) {
      if (emprendedor.getCategoria().equals(categoria)) {
        reporte.append(emprendedor.generarInformacion());
        reporte.append("---\n");
      }
    }

    return reporte.toString();
  }

  public String generarReportePorCategoriaAlternativo(GestorFeria gestor, String categoria) {
    StringBuilder resultado = new StringBuilder();

    resultado.append("REPORTE CATEGORIA ")
        .append(categoria)
        .append("\n");

    for (Emprendedor emprendedor : gestor.getEmprendedores()) {
      if (emprendedor.getCategoria().equals(categoria)) {
        resultado.append(emprendedor.getNombre()).append("\n");
      }
    }

    return resultado.toString();
  }

  public double calcularVentasTotales(GestorFeria gestor) {
    double total = 0;

    for (Venta venta : gestor.getVentas()) {
      total += venta.calcularTotalConDescuento();
    }

    return total;
  }

  public void imprimirResumenEjecutivo(GestorFeria gestor) {
    System.out.println("========== RESUMEN EJECUTIVO ==========");
    System.out.println("Total emprendedores: " + gestor.getEmprendedores().size());
    System.out.println("Total productos: " + gestor.getProductos().size());
    System.out.println("Total ventas: " + gestor.getVentas().size());
    System.out.println("Total facturado: $" + calcularVentasTotales(gestor));
    System.out.println("Emprendedores con stock bajo: " + gestor.getEmprendedoresConStockBajo().size());
    System.out.println("=======================================");
  }
}