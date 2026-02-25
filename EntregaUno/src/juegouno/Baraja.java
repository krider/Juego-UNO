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
            for (int i = 0; i <= 9; i++) {
                cartas.add(new Carta(color, i));
                cartas.add(new Carta(color, i));
            }
        }
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta robarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);
        }
        return null;
    }
}