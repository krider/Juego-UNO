package juegouno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TurnManagerTest {

    @Test
    void testAvanzarTurno() {

        List<Jugador> jugadores = Arrays.asList(
                new Jugador("A"),
                new Jugador("B"),
                new Jugador("C")
        );

        TurnManager tm = new TurnManager(jugadores);

        tm.avanzarTurno();

        assertEquals(1, tm.getTurnoActualIndex());
    }

    @Test
    void testReversaCambiaDireccion() {

        List<Jugador> jugadores = Arrays.asList(
                new Jugador("A"),
                new Jugador("B")
        );

        TurnManager tm = new TurnManager(jugadores);

        int inicio = tm.getTurnoActualIndex();

        tm.cambiarDireccion();
        tm.avanzarTurno();

        assertNotEquals(inicio, tm.getTurnoActualIndex());
    }
}