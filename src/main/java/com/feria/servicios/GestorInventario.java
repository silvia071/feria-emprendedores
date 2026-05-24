package com.feria.servicios;

import com.feria.modelos.Producto;
import com.feria.utils.Validadores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorInventario {
    private List<Producto> productos;

    public GestorInventario() {
        this.productos = new ArrayList<>();
    }

    public void registrarProducto(Producto producto) {
        if (producto != null && Validadores.validarPrecioStock(producto.getPrecio(), producto.getStock())) {
            this.productos.add(producto);
        }
    }

    public Producto crearProducto(String nombre, double precio, int stock, String categoria, String emprendedorId) {
        if (!Validadores.validarPrecioStock(precio, stock)) {
            System.out.println("Error: producto inválido");
            return null;
        }
        return new Producto(nombre, precio, stock, categoria, emprendedorId);
    }

    public Producto buscarProducto(String emprendedorId, String productoNombre) {
        for (Producto producto : productos) {
            if (producto.perteneceA(emprendedorId) && producto.tieneNombre(productoNombre)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }
}
