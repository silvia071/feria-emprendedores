package com.feria.modelos;

public class Producto {

  private String nombre;
  private double precio;
  private int stock;
  private String categoriaProducto;
  private String emprendedorId;

  public Producto(String nombre, double precio, int stock, String categoriaProducto, String emprendedorId) {
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
    this.categoriaProducto = categoriaProducto;
    this.emprendedorId = emprendedorId;
  }

  public double valorTotal() {
    return precio * stock;
  }

  public String mostrar() {
    return nombre + " - $" + precio + " (stock: " + stock + ")";
  }

  public boolean tieneStockBajo() {
    return stock < 5;
  }

  public boolean perteneceA(String emprendedorId) {
    return this.emprendedorId.equals(emprendedorId);
  }

  public boolean tieneNombre(String nombreProducto) {
    return this.nombre.equals(nombreProducto);
  }

  public boolean tieneStockSuficiente(int cantidad) {
    return stock >= cantidad;
  }

  public void descontarStock(int cantidad) {
    if (cantidad > 0 && cantidad <= stock) {
      stock -= cantidad;
    }
  }

  public boolean esPremium() {
    return precio > 10000;
  }

  public String getNombre() {
    return nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public int getStock() {
    return stock;
  }

  public String getCategoriaProducto() {
    return categoriaProducto;
  }

  public String getEmprendedorId() {
    return emprendedorId;
  }
}