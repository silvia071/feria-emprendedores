package com.feria.servicios;

import com.feria.modelos.Emprendedor;
import com.feria.utils.Validadores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorEmprendedores {
    private List<Emprendedor> emprendedores;

    public GestorEmprendedores() {
        this.emprendedores = new ArrayList<>();
    }

    public void registrarEmprendedor(Emprendedor emprendedor) {
        if (emprendedor != null && emprendedor.esValido()) {
            this.emprendedores.add(emprendedor);
        }
    }

    public boolean datosEmprendedorValidos(String nombre, String telefono, String email, String categoria) {
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

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor emprendedor : emprendedores) {
            if (emprendedor.tieneProductoConStockBajo()) {
                resultado.add(emprendedor);
            }
        }
        return resultado;
    }

    public List<Emprendedor> getEmprendedores() {
        return Collections.unmodifiableList(emprendedores);
    }
}