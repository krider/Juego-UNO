package juegouno;

/**
 * Clase principal que inicia la ejecución del juego UNO.
 *
 * @author juegouno
 * @version 1.0
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * Crea una instancia del juego e inicia la partida.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {

        Juego juego = new Juego();
        juego.iniciarJuego();
    }
}