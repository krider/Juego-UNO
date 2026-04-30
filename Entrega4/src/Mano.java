package juegouno;

import java.util.ArrayList;

/**
 * Representa la mano de cartas de un jugador en el juego UNO.
 * <p>
 * Gestiona la colección de cartas que tiene un jugador en un momento dado,
 * permitiendo agregar, quitar y consultar cartas.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class Mano {

    /** Lista interna de cartas en la mano del jugador. */
    private ArrayList<Carta> cartas;

    /**
     * Constructor que inicializa una mano vacía.
     */
    public Mano() {
        cartas = new ArrayList<>();
    }

    /**
     * Agrega una carta a la mano del jugador.
     *
     * @param c La carta que se va a agregar.
     */
    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    /**
     * Quita y devuelve la carta en la posición indicada.
     *
     * @param index El índice de la carta a quitar (basado en 0).
     * @return La carta que fue removida.
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango de la mano.
     */
    public Carta quitarCarta(int index) {
        return cartas.remove(index);
    }

    /**
     * Quita una carta específica de la mano por referencia de objeto.
     *
     * @param c La carta que se desea quitar de la mano.
     */
    public void quitarCarta(Carta c) {
        cartas.remove(c);
    }

    /**
     * Devuelve la carta en la posición indicada sin quitarla de la mano.
     *
     * @param index El índice de la carta (basado en 0).
     * @return La carta en la posición indicada.
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango de la mano.
     */
    public Carta getCarta(int index) {
        return cartas.get(index);
    }

    /**
     * Devuelve el número de cartas en la mano.
     *
     * @return La cantidad de cartas actuales en la mano.
     */
    public int size() {
        return cartas.size();
    }

    /**
     * Verifica si la mano del jugador está vacía.
     *
     * @return {@code true} si la mano no tiene cartas, {@code false} en caso contrario.
     */
    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    /**
     * Devuelve la lista completa de cartas en la mano.
     *
     * @return La lista de cartas del jugador.
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * Muestra en consola todas las cartas de la mano con sus índices.
     *
     * @param nombreJugador El nombre del jugador dueño de la mano.
     */
    public void mostrar(String nombreJugador) {

        System.out.println("\nCartas de " + nombreJugador + ":");

        for (int i = 0; i < cartas.size(); i++) {
            System.out.printf("%-20s", i + ":");
        }
        System.out.println();

        for (Carta c : cartas) {
            System.out.printf("%-20s", c);
        }
        System.out.println("\n");
    }
}