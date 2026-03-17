package juegouno;

public class Carta {

    String color;
    int numero;
    String tipo; // Nos indica aqui si es  NUMERO, SALTO, REVERSA, ROBA2, ROBA4, COMODIN

    // Cartas numericas
    public Carta(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = "NUMERO";
    }

    // Cartas especiales
    public Carta(String color, String tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
    }

    public boolean esValida(Carta otra) {

        // COMODINES siempre validos
        if (this.tipo.equals("COMODIN") || this.tipo.equals("ROBA4"))
            return true;

        // Mismo color
        if (this.color.equalsIgnoreCase(otra.color))
            return true;

        // Cartas numericas
        if (this.tipo.equals("NUMERO") && otra.tipo.equals("NUMERO"))
            return this.numero == otra.numero;

        // Mismo tipo especial
        return this.tipo.equals(otra.tipo);
    }

    @Override
    public String toString() {
        if (tipo.equals("NUMERO"))
            return color + " " + numero;
        else
            return color + " " + tipo;
    }
}