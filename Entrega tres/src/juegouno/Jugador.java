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
        if (carta != null)
            mano.add(carta);
    }

    public void mostrarManoHorizontal() {

        System.out.println("\nCartas de " + nombre + ":");

        for (int i = 0; i < mano.size(); i++) {
            System.out.printf("%-20s", i + ":");
        }
        System.out.println();

        for (Carta c : mano) {
            System.out.printf("%-20s", c);
        }
        System.out.println("\n");
    }
}