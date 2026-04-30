package juegouno;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa la baraja completa del juego UNO.
 * <p>
 * Contiene todas las cartas del juego: cartas numéricas del 0 al 9 en cuatro colores,
 * cartas especiales (SALTO, REVERSA, ROBA2) y comodines (COMODIN, ROBA4).
 * Al crearse, la baraja se genera y se baraja automáticamente.
 * </p>
 *
 * @author juegouno
 * @version 1.0
 */
public class Baraja {

    /** Lista interna que almacena todas las cartas de la baraja. */
    ArrayList<Carta> cartas;

    /**
     * Constructor que crea y baraja la baraja completa del juego UNO.
     * Se generan 108 cartas en total siguiendo las reglas oficiales del juego.
     */
    public Baraja() {
        cartas = new ArrayList<>();
        crearBaraja();
        barajar();
    }

    /**
     * Genera todas las cartas de la baraja siguiendo las reglas del UNO.
     * <ul>
     *   <li>1 carta del número 0 por cada color</li>
     *   <li>2 cartas de los números 1-9 por cada color</li>
     *   <li>2 cartas de SALTO, REVERSA y ROBA2 por cada color</li>
     *   <li>4 cartas de COMODIN y 4 cartas de ROBA4</li>
     * </ul>
     */
    private void crearBaraja() {

        String[] colores = {"Rojo", "Azul", "Verde", "Amarillo"};

        for (String color : colores) {

            cartas.add(new Carta(color, 0));

            for (int i = 1; i <= 9; i++) {
                cartas.add(new Carta(color, i));
                cartas.add(new Carta(color, i));
            }

            for (int i = 0; i < 2; i++) {
                cartas.add(new Carta(color, "SALTO"));
                cartas.add(new Carta(color, "REVERSA"));
                cartas.add(new Carta(color, "ROBA2"));
            }
        }

        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta("Negro", "COMODIN"));
            cartas.add(new Carta("Negro", "ROBA4"));
        }
    }

    /**
     * Baraja aleatoriamente todas las cartas de la baraja.
     * Utiliza {@link Collections#shuffle(java.util.List)} para mezclarlas.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Extrae y devuelve la carta en la cima de la baraja.
     *
     * @return La primera carta de la baraja, o {@code null} si la baraja está vacía.
     */
    public Carta robarCarta() {
        if (!cartas.isEmpty())
            return cartas.remove(0);
        return null;
    }
}