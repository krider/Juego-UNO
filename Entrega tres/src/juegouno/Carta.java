package juegouno;

public class Carta {

    String color;
    int numero;
    String tipo;

    public Carta(String color, int numero) {
        this.color = color;
        this.numero = numero;
        this.tipo = "NUMERO";
    }

    public Carta(String color, String tipo) {
        this.color = color;
        this.tipo = tipo;
        this.numero = -1;
    }

    public boolean esValida(Carta otra) {

        if (this.tipo.equals("COMODIN") || this.tipo.equals("ROBA4"))
            return true;

        if (this.color.equalsIgnoreCase(otra.color))
            return true;

        if (this.tipo.equals("NUMERO") && otra.tipo.equals("NUMERO"))
            return this.numero == otra.numero;

        return this.tipo.equals(otra.tipo);
    }

    @Override
    public String toString() {

        String icono;

        switch (color.toLowerCase()) {
            case "rojo": icono = "🔴"; break;
            case "azul": icono = "🔵"; break;
            case "verde": icono = "🟢"; break;
            case "amarillo": icono = "🟡"; break;
            default: icono = "⚫"; break;
        }

        if (tipo.equals("NUMERO"))
            return icono + " " + color.toLowerCase() + " " + numero;
        else
            return icono + " " + color.toLowerCase() + " " + tipo;
    }
}