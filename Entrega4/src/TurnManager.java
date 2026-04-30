package juegouno;

import java.util.List;

/**
 * Gestiona el orden de turnos en el juego UNO.
 * <p>
 * Controla qué jugador tiene el turno actual, la dirección del juego
 * (horaria o antihoraria) y proporciona métodos para avanzar, saltar
 * y cambiar la dirección de los turnos.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class TurnManager {

    /** Lista de jugadores que participan en la partida. */
    private List<Jugador> jugadores;

    /** Índice del jugador que tiene el turno actual. */
    private int turnoActual;

    /**
     * Dirección del juego.
     * {@code 1} significa dirección normal (izquierda),
     * {@code -1} significa dirección inversa (derecha).
     */
    private int direccion;

    /**
     * Constructor que inicializa el manejador de turnos.
     * El turno comienza en el primer jugador (índice 0) con dirección normal.
     *
     * @param jugadores Lista de jugadores que participan en la partida.
     */
    public TurnManager(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.direccion = 1;
    }

    /**
     * Devuelve el jugador que tiene el turno actual.
     *
     * @return El jugador actual.
     */
    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    /**
     * Devuelve el índice del jugador con el turno actual.
     *
     * @return El índice del turno actual en la lista de jugadores.
     */
    public int getTurnoActualIndex() {
        return turnoActual;
    }

    /**
     * Avanza el turno al siguiente jugador según la dirección actual.
     * Utiliza aritmética modular para que el turno sea circular.
     */
    public void avanzarTurno() {
        turnoActual = (turnoActual + direccion + jugadores.size()) % jugadores.size();
    }

    /**
     * Salta el turno del siguiente jugador, avanzando dos posiciones.
     * Se usa cuando se juega una carta de tipo SALTO.
     */
    public void saltarTurno() {
        avanzarTurno();
    }

    /**
     * Invierte la dirección del juego.
     * Si el juego iba en dirección normal, pasa a inversa, y viceversa.
     * Se usa cuando se juega una carta de tipo REVERSA.
     */
    public void cambiarDireccion() {
        direccion *= -1;
    }

    /**
     * Devuelve el siguiente jugador en turno sin modificar el turno actual.
     * Se usa para identificar quién debe robar cartas al jugar ROBA2 o ROBA4.
     *
     * @return El jugador que jugaría a continuación.
     */
    public Jugador getSiguienteJugador() {
        int siguiente = (turnoActual + direccion + jugadores.size()) % jugadores.size();
        return jugadores.get(siguiente);
    }
}