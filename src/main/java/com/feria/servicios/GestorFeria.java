package com.feria.servicios;

import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;
import com.feria.utils.Validadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorFeria {

  private List<Emprendedor> emprendedores;
  private List<Producto> productos;
  private List<Venta> ventas;

  public GestorFeria() {
    this.emprendedores = new ArrayList<>();
    this.productos = new ArrayList<>();
    this.ventas = new ArrayList<>();
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
    if (!datosEmprendedorValidos(nombre, telefono, email, categoria)) {
      return;
    }

    Emprendedor emprendedor = new Emprendedor(nombre, id, telefono, email, categoria);

    for (int i = 0; i < nombresProductos.size(); i++) {
      Producto producto = crearProducto(
          nombresProductos.get(i),
          precios.get(i),
          stocks.get(i),
          categoria,
          id);

      if (producto != null) {
        emprendedor.agregarProducto(producto);
        productos.add(producto);
      }
    }

    emprendedores.add(emprendedor);
    System.out.println("Emprendedor registrado con " + emprendedor.getProductos().size() + " productos");
  }

  public void registrarEmprendedor(Emprendedor emprendedor) {
    if (emprendedor != null && emprendedor.esValido()) {
      emprendedores.add(emprendedor);

      for (Producto producto : emprendedor.getProductos()) {
        productos.add(producto);
      }
    }
  }

  public void registrarProducto(Producto producto) {
    if (producto != null && Validadores.validarPrecioStock(producto.getPrecio(), producto.getStock())) {
      productos.add(producto);
    }
  }

  public void registrarVenta(
      String idVenta,
      String emprendedorId,
      String productoNombre,
      int cantidad,
      double precio,
      String fecha) {
    Producto productoEncontrado = buscarProducto(emprendedorId, productoNombre);

    if (productoEncontrado == null) {
      System.out.println("Producto no encontrado");
      return;
    }

    if (!productoEncontrado.tieneStockSuficiente(cantidad)) {
      System.out.println("Stock insuficiente");
      return;
    }

    Venta venta = new Venta(idVenta, emprendedorId, productoNombre, cantidad, precio, fecha);
    ventas.add(venta);
    productoEncontrado.descontarStock(cantidad);

    System.out.println("Venta registrada. Nuevo stock: " + productoEncontrado.getStock());
  }

  public List<Emprendedor> getEmprendedoresConStockBajo() {
    List<Emprendedor> resultado = new ArrayList<>();

    for (Emprendedor emprendedor : emprendedores) {
      if (emprendedor.tieneProductoConStockBajo()) {
        resultado.add(emprendedor);
      }
    }

    return resultado;
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

  private boolean datosEmprendedorValidos(String nombre, String telefono, String email, String categoria) {
    if (!Validadores.nombreValido(nombre)) {
      System.out.println("Error: nombre inválido");
      return false;
    }

    if (!Validadores.telefonoValido(telefono)) {
      System.out.println("Error: teléfono inválido");
      return false;
    }

    if (!Validadores.emailValido(email)) {
      System.out.println("Error: email inválido");
      return false;
    }

    if (!Validadores.categoriaPermitida(categoria)) {
      System.out.println("Error: categoría inválida");
      return false;
    }

    return true;
  }

  private Producto crearProducto(
      String nombre,
      double precio,
      int stock,
      String categoria,
      String emprendedorId) {
    if (!Validadores.validarPrecioStock(precio, stock)) {
      System.out.println("Error: producto inválido");
      return null;
    }

    return new Producto(nombre, precio, stock, categoria, emprendedorId);
  }

  private Producto buscarProducto(String emprendedorId, String productoNombre) {
    for (Producto producto : productos) {
      if (producto.perteneceA(emprendedorId) && producto.tieneNombre(productoNombre)) {
        return producto;
      }
    }

    return null;
  }

  public List<Emprendedor> getEmprendedores() {
    return Collections.unmodifiableList(emprendedores);
  }

  public List<Producto> getProductos() {
    return Collections.unmodifiableList(productos);
  }

  public List<Venta> getVentas() {
    return Collections.unmodifiableList(ventas);
  }
}