package juegouno;

import java.util.Random;

/**
 * Motor de reglas del juego UNO.
 * <p>
 * Se encarga de validar si un movimiento es permitido y de aplicar
 * los efectos de las cartas especiales como SALTO, REVERSA, ROBA2,
 * ROBA4 y COMODIN.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class RuleEngine {

    /**
     * Verifica si una carta puede jugarse sobre la carta actual de la mesa.
     *
     * @param jugada La carta que el jugador desea jugar.
     * @param mesa   La carta que está actualmente en la pila de descarte.
     * @return {@code true} si el movimiento es válido, {@code false} en caso contrario.
     */
    public boolean esMovimientoValido(Carta jugada, Carta mesa) {
        return jugada.esValida(mesa);
    }

    /**
     * Aplica el efecto especial de la carta jugada sobre el estado del juego.
     * <p>
     * Efectos según el tipo de carta:
     * <ul>
     *   <li><b>SALTO</b>: salta el turno del siguiente jugador.</li>
     *   <li><b>REVERSA</b>: invierte la dirección del juego.</li>
     *   <li><b>ROBA2</b>: el siguiente jugador roba 2 cartas y pierde su turno.</li>
     *   <li><b>ROBA4</b>: el siguiente jugador roba 4 cartas, pierde su turno y se cambia el color.</li>
     *   <li><b>COMODIN</b>: cambia el color activo del juego.</li>
     * </ul>
     * </p>
     *
     * @param carta          La carta que fue jugada.
     * @param jugadorActual  El jugador que jugó la carta.
     * @param turnManager    El manejador de turnos del juego.
     * @param baraja         La baraja de donde se roban cartas.
     * @param discard        La pila de descarte del juego.
     * @param esHumano       {@code true} si el jugador es humano, {@code false} si es IA.
     * @param colorElegido   El color elegido por el jugador humano (solo aplica para COMODIN y ROBA4).
     */
    public void aplicarEfecto(
            Carta carta,
            Jugador jugadorActual,
            TurnManager turnManager,
            Baraja baraja,
            DiscardPile discard,
            boolean esHumano,
            String colorElegido
    ) {

        switch (carta.tipo) {

            case "SALTO":
                turnManager.saltarTurno();
                break;

            case "REVERSA":
                turnManager.cambiarDireccion();
                break;

            case "ROBA2":
                aplicarRobo(turnManager, baraja, 2);
                turnManager.avanzarTurno();
                break;

            case "ROBA4":
                aplicarRobo(turnManager, baraja, 4);
                cambiarColor(carta, jugadorActual, esHumano, colorElegido);
                turnManager.avanzarTurno();
                break;

            case "COMODIN":
                cambiarColor(carta, jugadorActual, esHumano, colorElegido);
                break;
        }
    }

    /**
     * Hace que el siguiente jugador robe una cantidad determinada de cartas.
     * Imprime en consola un aviso con el nombre del jugador y cuántas cartas robó.
     *
     * @param tm       El manejador de turnos para identificar al siguiente jugador.
     * @param baraja   La baraja de la cual se roban las cartas.
     * @param cantidad El número de cartas que debe robar el siguiente jugador.
     */
    private void aplicarRobo(TurnManager tm, Baraja baraja, int cantidad) {

        Jugador siguiente = tm.getSiguienteJugador();

        for (int i = 0; i < cantidad; i++) {
            siguiente.robar(baraja);
        }

        System.out.println("Aviso: " + siguiente.nombre + " roba " + cantidad + " cartas");
    }

    /**
     * Cambia el color activo de la carta comodín jugada.
     * <p>
     * Si es un jugador humano, usa el color que eligió.
     * Si es la IA, elige un color aleatorio entre los cuatro colores disponibles.
     * </p>
     *
     * @param carta          La carta comodín cuyo color se va a cambiar.
     * @param jugador        El jugador que jugó la carta.
     * @param humano         {@code true} si el jugador es humano.
     * @param colorElegido   El color seleccionado por el humano (ignorado si es IA).
     */
    private void cambiarColor(Carta carta, Jugador jugador, boolean humano, String colorElegido) {

        if (humano) {
            carta.color = colorElegido;
        } else {
            String[] colores = {"Rojo", "Azul", "Verde", "Amarillo"};
            carta.color = colores[new Random().nextInt(4)];
            System.out.println(jugador.nombre + " cambia a " + carta.color);
        }
    }
}