package com.feria.servicios;

import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;
import java.util.List;

public class GestorFeria {

  
  private GestorEmprendedores gestorEmprendedores;
  private GestorInventario gestorInventario;
  private GestorVentas gestorVentas;

  public GestorFeria() {
    this.gestorEmprendedores = new GestorEmprendedores();
    this.gestorInventario = new GestorInventario();
    this.gestorVentas = new GestorVentas();
  }

  public void registrarEmprendedorConProductos(
      String nombre,
      String id,
      String telefono,
      String email,
      String categoria,
      List<String> nombresProductos,
      List<Double> precios,
      List<Integer> stocks) {
      
    if (!gestorEmprendedores.datosEmprendedorValidos(nombre, telefono, email, categoria)) {
      return;
    }

    Emprendedor emprendedor = new Emprendedor(nombre, id, telefono, email, categoria);

    for (int i = 0; i < nombresProductos.size(); i++) {
      Producto producto = gestorInventario.crearProducto(
          nombresProductos.get(i),
          precios.get(i),
          stocks.get(i),
          categoria,
          id);

      if (producto != null) {
        emprendedor.agregarProducto(producto);
        gestorInventario.registrarProducto(producto);
      }
    }

    gestorEmprendedores.registrarEmprendedor(emprendedor);
    System.out.println("Emprendedor registrado con " + emprendedor.getProductos().size() + " productos");
  }

  public void registrarEmprendedor(Emprendedor emprendedor) {
    if (emprendedor != null && emprendedor.esValido()) {
      gestorEmprendedores.registrarEmprendedor(emprendedor);
      for (Producto producto : emprendedor.getProductos()) {
        gestorInventario.registrarProducto(producto);
      }
    }
  }

  public void registrarProducto(Producto producto) {
    gestorInventario.registrarProducto(producto);
  }

  public void registrarVenta(
      String idVenta,
      String emprendedorId,
      String productoNombre,
      int cantidad,
      double precio,
      String fecha) {
      
    Producto productoEncontrado = gestorInventario.buscarProducto(emprendedorId, productoNombre);

    if (productoEncontrado == null) {
      System.out.println("Producto no encontrado");
      return;
    }

    if (!productoEncontrado.tieneStockSuficiente(cantidad)) {
      System.out.println("Stock insuficiente");
      return;
    }

    Venta venta = new Venta(idVenta, emprendedorId, productoNombre, cantidad, precio, fecha);
    gestorVentas.registrarVenta(venta);
    productoEncontrado.descontarStock(cantidad);

    System.out.println("Venta registrada. Nuevo stock: " + productoEncontrado.getStock());
  }

  public List<Emprendedor> getEmprendedoresConStockBajo() {
    return gestorEmprendedores.getEmprendedoresConStockBajo();
  }

  public void procesarVentasPendientesYCobrar() {
    gestorVentas.procesarVentasPendientesYCobrar();
  }

  // Los getters públicos delegan a cada módulo interno para no romper Reportes ni Main
  public List<Emprendedor> getEmprendedores() {
    return gestorEmprendedores.getEmprendedores();
  }

  public List<Producto> getProductos() {
    return gestorInventario.getProductos();
  }

  public List<Venta> getVentas() {
    return gestorVentas.getVentas();
  }
}
