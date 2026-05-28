package com.feria.modelos;

import com.feria.utils.Validadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Emprendedor {

  private String nombre;
  private String id;
  private String telefono;
  private String email;
  private String categoria;
  private List<Producto> productos;

  public Emprendedor(String nombre, String id, String telefono, String email, String categoria) {
    this.nombre = nombre;
    this.id = id;
    this.telefono = telefono;
    this.email = email;
    this.categoria = categoria;
    this.productos = new ArrayList<>();
  }

  public String generarInformacion() {
    StringBuilder informacion = new StringBuilder();

    informacion.append("Emprendedor: ").append(nombre).append("\n");
    informacion.append("ID: ").append(id).append("\n");
    informacion.append("Contacto: ").append(telefono).append(" | ").append(email).append("\n");
    informacion.append("Categoria: ").append(categoria).append("\n");
    informacion.append("Productos:\n");

    for (Producto producto : productos) {
      informacion.append(" - ")
          .append(producto.getNombre())
          .append(" ($")
          .append(producto.getPrecio())
          .append(")\n");
    }

    return informacion.toString();
  }

  public boolean esValido() {
    return Validadores.validarEmprendedorCompleto(this);
  }

  public void agregarProducto(Producto producto) {
    if (producto != null) {
      productos.add(producto);
    }
  }

  public double calcularValorTotalStock() {
    double total = 0;

    for (Producto producto : productos) {
      total += producto.valorTotal();
    }

    return total;
  }

  public boolean tieneProductoConStockBajo() {
    for (Producto producto : productos) {
      if (producto.tieneStockBajo()) {
        return true;
      }
    }

    return false;
  }

  public String getNombre() {
    return nombre;
  }

  public String getId() {
    return id;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }

  public String getCategoria() {
    return categoria;
  }

  public List<Producto> getProductos() {
    return Collections.unmodifiableList(productos);
  }
}