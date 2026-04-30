package juegouno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class RuleEngineTest {

    @Test
    void testReversaCambiaDireccion() {

        // Con 3 jugadores: normal sería A(0) -> B(1) -> C(2)
        // Con reversa:               A(0) -> C(2) -> B(1)
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("A"));
        jugadores.add(new Jugador("B"));
        jugadores.add(new Jugador("C")); // <-- tercer jugador necesario

        TurnManager tm = new TurnManager(jugadores);
        RuleEngine engine = new RuleEngine();

        engine.aplicarEfecto(
                new Carta("Rojo", "REVERSA"),
                jugadores.get(0),
                tm,
                new Baraja(),
                new DiscardPile(),
                false,
                ""
        );

        tm.avanzarTurno();
        int despues = tm.getTurnoActualIndex();

        // Sin reversa el siguiente sería B (índice 1)
        // Con reversa el siguiente debe ser C (índice 2)
        assertEquals(2, despues);
    }

    @Test
    void testComodinCambiaColorHumano() {

        RuleEngine engine = new RuleEngine();
        Carta comodin = new Carta("Negro", "COMODIN");

        engine.aplicarEfecto(
                comodin,
                new Jugador("A"),
                new TurnManager(Arrays.asList(new Jugador("A"), new Jugador("B"))),
                new Baraja(),
                new DiscardPile(),
                true,
                "Azul"
        );

        assertEquals("Azul", comodin.color);
    }

    @Test
    void testComodinCambiaColorIA() {

        RuleEngine engine = new RuleEngine();
        Carta comodin = new Carta("Negro", "COMODIN");

        engine.aplicarEfecto(
                comodin,
                new Jugador("Bot"),
                new TurnManager(Arrays.asList(new Jugador("A"), new Jugador("B"))),
                new Baraja(),
                new DiscardPile(),
                false,
                ""
        );

        assertNotEquals("Negro", comodin.color);
    }

    @Test
    void testRoba2HaceRobar2Cartas() {

        Jugador j1 = new Jugador("A");
        Jugador j2 = new Jugador("B");

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(j1);
        jugadores.add(j2);

        TurnManager tm = new TurnManager(jugadores);
        RuleEngine engine = new RuleEngine();
        Baraja baraja = new Baraja();

        int antes = j2.mano.size();

        engine.aplicarEfecto(
                new Carta("Rojo", "ROBA2"),
                j1,
                tm,
                baraja,
                new DiscardPile(),
                false,
                ""
        );

        int despues = j2.mano.size();

        assertEquals(antes + 2, despues);
    }

    @Test
    void testRoba4HaceRobar4Cartas() {

        Jugador j1 = new Jugador("A");
        Jugador j2 = new Jugador("B");

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(j1);
        jugadores.add(j2);

        TurnManager tm = new TurnManager(jugadores);
        RuleEngine engine = new RuleEngine();
        Baraja baraja = new Baraja();

        int antes = j2.mano.size();

        engine.aplicarEfecto(
                new Carta("Negro", "ROBA4"),
                j1,
                tm,
                baraja,
                new DiscardPile(),
                true,
                "Rojo"
        );

        int despues = j2.mano.size();

        assertEquals(antes + 4, despues);
    }
}