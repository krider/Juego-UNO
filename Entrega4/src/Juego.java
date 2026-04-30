package juegouno;

import java.util.*;

/**
 * Clase principal que controla el flujo del juego UNO.
 * <p>
 * Gestiona la partida completa: inicializa los jugadores, reparte cartas,
 * controla el ciclo de turnos y determina al ganador.
 * Soporta un jugador humano y tres jugadores controlados por la IA.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class Juego {

    /** La baraja de cartas del juego. */
    private Baraja baraja = new Baraja();

    /** Lista de jugadores que participan en la partida. */
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    /** Manejador de turnos que controla el orden y dirección del juego. */
    private TurnManager turnManager;

    /** Motor de reglas que valida movimientos y aplica efectos de cartas. */
    private RuleEngine ruleEngine = new RuleEngine();

    /** Pila de descarte que almacena la carta actual en mesa. */
    private DiscardPile discard = new DiscardPile();

    /** Scanner para leer la entrada del jugador humano desde consola. */
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor que inicializa una nueva partida de UNO.
     * Crea la baraja, la lista de jugadores y la pila de descarte.
     */
    public Juego() {
    }

    /**
     * Inicia y ejecuta la partida completa de UNO.
     * <p>
     * El flujo es el siguiente:
     * <ol>
     *   <li>Solicita el nombre del jugador humano.</li>
     *   <li>Crea los jugadores (1 humano + 3 IA).</li>
     *   <li>Reparte 7 cartas a cada jugador.</li>
     *   <li>Coloca una carta inicial válida en la mesa.</li>
     *   <li>Ejecuta el ciclo de turnos hasta que alguien gane.</li>
     * </ol>
     */
    public void iniciarJuego() {

        System.out.print("Ingresa tu nombre: ");
        String nombre = sc.nextLine();

        jugadores.add(new Jugador(nombre));
        jugadores.add(new Jugador("Pepe"));
        jugadores.add(new Jugador("Toña"));
        jugadores.add(new Jugador("Mari"));

        turnManager = new TurnManager(jugadores);

        // Repartir
        for (int i = 0; i < 7; i++)
            for (Jugador j : jugadores)
                j.robar(baraja);

        // Carta inicial válida
        Carta inicial;
        do {
            inicial = baraja.robarCarta();
        } while (inicial.tipo.equals("COMODIN") || inicial.tipo.equals("ROBA4"));

        discard.iniciar(inicial);

        while (true) {

            mostrarEstado();

            Jugador actual = turnManager.getJugadorActual();

            if (turnManager.getTurnoActualIndex() == 0)
                turnoHumano(actual);
            else
                turnoIA(actual);

            if (actual.mano.estaVacia()) {
                System.out.println("Felicidades " + actual.nombre + " has ganado!");
                break;
            }

            turnManager.avanzarTurno();
        }
    }

    /**
     * Muestra en consola el estado actual del juego.
     * <p>
     * Imprime la carta que está en la mesa y la cantidad de cartas
     * que tiene cada jugador en su mano.
     * </p>
     */
    private void mostrarEstado() {

        System.out.println("\n==============================");
        System.out.println("Carta mesa: " + discard.getCartaActual());

        for (Jugador j : jugadores)
            System.out.println(j.nombre + ": " + j.mano.size() + " cartas");

        System.out.println("==============================");
    }

    /**
     * Ejecuta el turno del jugador humano.
     * <p>
     * Muestra la mano del jugador y solicita que elija una carta por índice
     * o escriba "R" para robar. Repite hasta que el jugador haga un movimiento válido.
     * </p>
     *
     * @param j El jugador humano cuyo turno se va a ejecutar.
     */
    private void turnoHumano(Jugador j) {

        while (true) {

            j.mostrarMano();

            System.out.print("Elige carta o R: ");
            String input = sc.next();

            if (input.equalsIgnoreCase("R")) {
                Carta c = baraja.robarCarta();
                j.mano.agregarCarta(c);
                System.out.println("Robaste: " + c);
                return;
            }

            try {
                int i = Integer.parseInt(input);
                Carta c = j.mano.getCarta(i);

                if (ruleEngine.esMovimientoValido(c, discard.getCartaActual())) {

                    j.mano.quitarCarta(i);
                    jugarCarta(j, c, true);
                    return;

                } else {
                    System.out.println("Movimiento inválido");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida");
            }
        }
    }

    /**
     * Ejecuta el turno de un jugador controlado por la IA.
     * <p>
     * La IA busca la primera carta válida en su mano y la juega.
     * Si no tiene ninguna carta válida, roba una carta de la baraja
     * y la juega si es válida.
     * </p>
     *
     * @param j El jugador IA cuyo turno se va a ejecutar.
     */
    private void turnoIA(Jugador j) {

        System.out.println(j.nombre + " juega...");

        for (Carta c : j.mano.getCartas()) {

            if (ruleEngine.esMovimientoValido(c, discard.getCartaActual())) {

                j.mano.quitarCarta(c);
                jugarCarta(j, c, false);
                return;
            }
        }

        Carta c = baraja.robarCarta();
        j.mano.agregarCarta(c);

        if (ruleEngine.esMovimientoValido(c, discard.getCartaActual())) {
            j.mano.quitarCarta(c);
            jugarCarta(j, c, false);
        }
    }

    /**
     * Procesa el efecto de jugar una carta en la mesa.
     * <p>
     * Coloca la carta en la pila de descarte, muestra el mensaje de UNO
     * automáticamente si el jugador le queda una sola carta, y aplica
     * el efecto especial de la carta si corresponde.
     * </p>
     *
     * @param j      El jugador que está jugando la carta.
     * @param c      La carta que se va a jugar.
     * @param humano {@code true} si el jugador es humano, {@code false} si es IA.
     */
    private void jugarCarta(Jugador j, Carta c, boolean humano) {

        discard.ponerCarta(c);

        System.out.println(j.nombre + " juega: " + c);

        // UNO automático
        if (j.mano.size() == 1) {
            System.out.println("¡" + j.nombre.toUpperCase() + " DICE UNO!");
        }

        String colorElegido = "";

        if (c.tipo.equals("COMODIN") || c.tipo.equals("ROBA4")) {

            if (humano) {
                System.out.print("Elige color (Rojo/Azul/Verde/Amarillo): ");
                colorElegido = sc.next();
            }
        }

        ruleEngine.aplicarEfecto(
                c,
                j,
                turnManager,
                baraja,
                discard,
                humano,
                colorElegido
        );
    }
}