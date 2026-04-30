package juegouno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ManoTest {

    @Test
    void testAgregarCarta() {

        Mano mano = new Mano();
        mano.agregarCarta(new Carta("Rojo", 5));

        assertEquals(1, mano.size());
    }

    @Test
    void testQuitarCartaPorIndice() {

        Mano mano = new Mano();
        mano.agregarCarta(new Carta("Azul", 3));
        mano.agregarCarta(new Carta("Rojo", 7));

        mano.quitarCarta(0);

        assertEquals(1, mano.size());
    }

    @Test
    void testQuitarCartaPorObjeto() {

        Mano mano = new Mano();
        Carta carta = new Carta("Verde", 2);

        mano.agregarCarta(carta);
        mano.quitarCarta(carta);

        assertTrue(mano.estaVacia());
    }

    @Test
    void testGetCarta() {

        Mano mano = new Mano();
        Carta carta = new Carta("Amarillo", 9);

        mano.agregarCarta(carta);

        assertEquals(carta, mano.getCarta(0));
    }

    @Test
    void testEstaVacia() {

        Mano mano = new Mano();

        assertTrue(mano.estaVacia());

        mano.agregarCarta(new Carta("Rojo", 1));

        assertFalse(mano.estaVacia());
    }

    @Test
    void testSize() {

        Mano mano = new Mano();

        mano.agregarCarta(new Carta("Rojo", 1));
        mano.agregarCarta(new Carta("Azul", 2));
        mano.agregarCarta(new Carta("Verde", 3));

        assertEquals(3, mano.size());
    }

    // EXCEPCIONES
    @Test
    void testExcepcionGetCarta() {

        Mano mano = new Mano();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            mano.getCarta(0);
        });
    }

    @Test
    void testExcepcionQuitarCarta() {

        Mano mano = new Mano();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            mano.quitarCarta(5);
        });
    }
}