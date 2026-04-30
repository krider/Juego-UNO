package juegouno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CartaTest {

    @Test
    void testMovimientoValidoPorColor() {
        Carta mesa = new Carta("Rojo", 5);
        Carta jugada = new Carta("Rojo", 9);

        assertTrue(jugada.esValida(mesa));
    }

    @Test
    void testMovimientoValidoPorNumero() {
        Carta mesa = new Carta("Azul", 3);
        Carta jugada = new Carta("Rojo", 3);

        assertTrue(jugada.esValida(mesa));
    }

    @Test
    void testComodinSiempreValido() {
        Carta mesa = new Carta("Verde", 7);
        Carta comodin = new Carta("Negro", "COMODIN");

        assertTrue(comodin.esValida(mesa));
    }

    @Test
    void testRoba4SiempreValido() {
        Carta mesa = new Carta("Amarillo", 2);
        Carta roba4 = new Carta("Negro", "ROBA4");

        assertTrue(roba4.esValida(mesa));
    }

    @Test
    void testMovimientoInvalido() {
        Carta mesa = new Carta("Rojo", 5);
        Carta jugada = new Carta("Azul", 9);

        assertFalse(jugada.esValida(mesa));
    }
}