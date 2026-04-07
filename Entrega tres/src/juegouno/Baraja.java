package juegouno;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {

    ArrayList<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        crearBaraja();
        barajar();
    }

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

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta robarCarta() {
        if (!cartas.isEmpty())
            return cartas.remove(0);
        return null;
    }
}