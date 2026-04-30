package juegouno;

/**
 * Representa a un jugador del juego UNO.
 * <p>
 * Cada jugador tiene un nombre y una mano de cartas.
 * Puede ser un jugador humano o controlado por la IA.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class Jugador {

    /** Nombre del jugador. */
    String nombre;

    /** La mano del jugador con sus cartas actuales. */
    Mano mano;

    /**
     * Constructor que crea un jugador con el nombre dado y una mano vacía.
     *
     * @param nombre El nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
    }

    /**
     * Roba una carta de la baraja y la agrega a la mano del jugador.
     * Si la baraja está vacía, no se agrega ninguna carta.
     *
     * @param baraja La baraja de la cual se robará la carta.
     */
    public void robar(Baraja baraja) {
        Carta carta = baraja.robarCarta();
        if (carta != null)
            mano.agregarCarta(carta);
    }

    /**
     * Muestra en consola las cartas actuales en la mano del jugador.
     * Imprime el nombre del jugador y todas sus cartas con sus índices.
     */
    public void mostrarMano() {
        mano.mostrar(nombre);
    }
}