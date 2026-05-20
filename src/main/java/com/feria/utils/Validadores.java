package com.feria.utils;

import com.feria.modelos.Emprendedor;

public class Validadores {

  private static final String[] CATEGORIAS_PERMITIDAS = {
      "comida",
      "artesania",
      "tecnologia",
      "ropa"
  };

  public static boolean emailValido(String email) {
    return email != null && email.contains("@") && email.length() >= 5;
  }

  public static boolean telefonoValido(String telefono) {
    return telefono != null && telefono.length() >= 8;
  }

  public static boolean nombreValido(String nombre) {
    return nombre != null && nombre.length() >= 2;
  }

  public static boolean categoriaPermitida(String categoria) {
    if (categoria == null) {
      return false;
    }

    for (String categoriaPermitida : CATEGORIAS_PERMITIDAS) {
      if (categoriaPermitida.equals(categoria)) {
        return true;
      }
    }

    return false;
  }

  public static boolean validarPrecioStock(double precio, int stock) {
    return precio > 0 && stock >= 0;
  }

  public static boolean validarEmprendedorCompleto(Emprendedor emprendedor) {
    if (emprendedor == null) {
      return false;
    }

    return nombreValido(emprendedor.getNombre())
        && emailValido(emprendedor.getEmail())
        && telefonoValido(emprendedor.getTelefono())
        && categoriaPermitida(emprendedor.getCategoria());
  }
}