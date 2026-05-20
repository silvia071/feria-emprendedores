package com.feria.modelos;

public class Venta {

  private String idVenta;
  private String emprendedorId;
  private String productoNombre;
  private int cantidad;
  private double precioUnitario;
  private String fecha;
  private boolean pagoRealizado;

  public Venta(String idVenta, String emprendedorId, String productoNombre, int cantidad, double precioUnitario,
      String fecha) {
    this.idVenta = idVenta;
    this.emprendedorId = emprendedorId;
    this.productoNombre = productoNombre;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.fecha = fecha;
    this.pagoRealizado = false;
  }

  public double calcularSubtotal() {
    return cantidad * precioUnitario;
  }

  public double calcularTotalConDescuento() {
    double total = calcularSubtotal();

    if (cantidad > 10) {
      total *= 0.9;
    }

    if (total > 5000) {
      total *= 0.95;
    }

    return total;
  }

  public void registrarPago() {
    this.pagoRealizado = true;
  }

  public boolean estaPendienteDePago() {
    return !pagoRealizado;
  }

  public String generarRecibo() {
    StringBuilder recibo = new StringBuilder();

    recibo.append("=== RECIBO DE VENTA ===\n");
    recibo.append("Venta ID: ").append(idVenta).append("\n");
    recibo.append("Fecha: ").append(fecha).append("\n");
    recibo.append("Producto: ").append(productoNombre).append("\n");
    recibo.append("Cantidad: ").append(cantidad).append("\n");
    recibo.append("Precio unitario: $").append(precioUnitario).append("\n");
    recibo.append("Total con descuentos: $").append(calcularTotalConDescuento()).append("\n");
    recibo.append("Pago: ").append(pagoRealizado ? "Realizado" : "Pendiente").append("\n");

    return recibo.toString();
  }

  public String getIdVenta() {
    return idVenta;
  }

  public String getEmprendedorId() {
    return emprendedorId;
  }

  public String getProductoNombre() {
    return productoNombre;
  }

  public int getCantidad() {
    return cantidad;
  }

  public double getPrecioUnitario() {
    return precioUnitario;
  }

  public String getFecha() {
    return fecha;
  }

  public boolean isPagoRealizado() {
    return pagoRealizado;
  }
}