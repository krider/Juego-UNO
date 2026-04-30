package juegouno;

/**
 * Representa una carta del juego UNO.
 * <p>
 * Una carta puede ser de tipo numérico (0-9) o especial
 * (SALTO, REVERSA, ROBA2, COMODIN, ROBA4).
 * Las cartas de comodín son siempre válidas sin importar el color o tipo de la carta en mesa.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class Carta {

    /** Color de la carta (Rojo, Azul, Verde, Amarillo o Negro para comodines). */
    String color;

    /** Número de la carta. Vale -1 si la carta es de tipo especial. */
    int numero;

    /**
     * Tipo de la carta. Valores posibles:
     * {@code "NUMERO"}, {@code "SALTO"}, {@code "REVERSA"},
     * {@code "ROBA2"}, {@code "COMODIN"}, {@code "ROBA4"}.
     */
    String tipo;

    /**
     * Constructor para crear una carta numérica.
     *
     * @param color  El color de la carta (Rojo, Azul, Verde, Amarillo).
     * @param numero El número de la carta (0-9).
     */
    public Carta(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = "NUMERO";
    }

    /**
     * Constructor para crear una carta especial.
     *
     * @param color El color de la carta. Usar "Negro" para COMODIN y ROBA4.
     * @param tipo  El tipo especial de la carta (SALTO, REVERSA, ROBA2, COMODIN, ROBA4).
     */
    public Carta(String color, String tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
    }

    /**
     * Verifica si esta carta puede jugarse sobre la carta actual de la mesa.
     * <p>
     * Una carta es válida si:
     * <ul>
     *   <li>Es COMODIN o ROBA4 (siempre válidas)</li>
     *   <li>Comparte el mismo color que la carta de la mesa</li>
     *   <li>Ambas son numéricas y tienen el mismo número</li>
     *   <li>Ambas son del mismo tipo especial</li>
     * </ul>
     * </p>
     *
     * @param otra La carta que está actualmente en la mesa.
     * @return {@code true} si la carta es válida para jugar, {@code false} en caso contrario.
     */
    public boolean esValida(Carta otra) {

        if (this.tipo.equals("COMODIN") || this.tipo.equals("ROBA4"))
            return true;

        if (this.color.equalsIgnoreCase(otra.color))
            return true;

        if (this.tipo.equals("NUMERO") && otra.tipo.equals("NUMERO"))
            return this.numero == otra.numero;

        return this.tipo.equals(otra.tipo);
    }

    /**
     * Devuelve una representación legible de la carta.
     * <p>
     * Ejemplos: {@code "Rojo 5"}, {@code "Azul SALTO"}, {@code "Negro COMODIN"}.
     * </p>
     *
     * @return Cadena con el color y el número o tipo de la carta.
     */
    @Override
    public String toString() {

        String colorTexto = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();

        if (tipo.equals("NUMERO")) {
            return colorTexto + " " + numero;
        } else {
            return colorTexto + " " + tipo;
        }
    }
}