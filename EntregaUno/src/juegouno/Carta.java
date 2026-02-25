package juegouno;

public class Carta {
    String color;
    int numero;

    public Carta(String color, int numero) {
        this.color = color;
        this.numero = numero;
    }

    public boolean esValida(Carta otra) {
        return this.color.equals(otra.color) || this.numero == otra.numero;
    }

    @Override
    public String toString() {
        return color + " " + numero;
    }
}