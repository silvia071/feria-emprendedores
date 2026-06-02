package com.feria.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadoresTest {

    @Test
    void emailValido() {

        assertTrue(
                Validadores.emailValido(
                        "usuario@gmail.com"
                )
        );
    }

    @Test
    void emailInvalido() {

        assertFalse(
                Validadores.emailValido(
                        "usuariogmail.com"
                )
        );
    }

    @Test
    void categoriaInvalida() {

        assertFalse(
                Validadores.categoriaPermitida(
                        "vehiculos"
                )
        );
    }
}