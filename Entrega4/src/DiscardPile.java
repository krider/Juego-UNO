package juegouno;

import java.util.ArrayList;

/**
 * Representa la pila de descarte del juego UNO.
 * <p>
 * Almacena la carta que está actualmente en juego sobre la mesa
 * y lleva un historial de todas las cartas jugadas anteriormente.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class DiscardPile {

    /** La carta que está actualmente en la cima de la pila de descarte. */
    private Carta cartaActual;

    /** Historial de todas las cartas que han sido jugadas anteriormente. */
    private ArrayList<Carta> historial;

    /**
     * Constructor que inicializa la pila de descarte vacía.
     */
    public DiscardPile() {
        historial = new ArrayList<>();
    }

    /**
     * Establece la carta inicial de la pila de descarte al comenzar el juego.
     *
     * @param cartaInicial La primera carta que se coloca en la mesa.
     */
    public void iniciar(Carta cartaInicial) {
        this.cartaActual = cartaInicial;
    }

    /**
     * Devuelve la carta que está actualmente en la cima de la pila de descarte.
     *
     * @return La carta actual en la mesa.
     */
    public Carta getCartaActual() {
        return cartaActual;
    }

    /**
     * Coloca una nueva carta en la pila de descarte.
     * La carta actual pasa al historial y la nueva carta se convierte en la actual.
     *
     * @param nueva La carta que se va a colocar sobre la pila.
     */
    public void ponerCarta(Carta nueva) {
        historial.add(cartaActual);
        cartaActual = nueva;
    }

    /**
     * Devuelve el historial completo de cartas jugadas.
     *
     * @return Lista con todas las cartas que han pasado por la pila de descarte.
     */
    public ArrayList<Carta> getHistorial() {
        return historial;
    }
}