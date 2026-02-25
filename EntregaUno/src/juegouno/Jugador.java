package juegouno;

import java.util.ArrayList;

public class Jugador {
    String nombre;
    ArrayList<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    public void robar(Baraja baraja) {
        Carta carta = baraja.robarCarta();
        if (carta != null) {
            mano.add(carta);
        }
    }

    public void mostrarMano() {
        System.out.println("Cartas de " + nombre + ":");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ": " + mano.get(i));
        }
    }
}