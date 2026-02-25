package juegouno;

import java.util.Scanner;

public class Juego {
    Baraja baraja;
    Jugador humano;
    Jugador computadora;
    Carta cartaMesa;
    Scanner sc;

    public Juego() {
        baraja = new Baraja();
        humano = new Jugador("Jugador H1");
        computadora = new Jugador("Computadora");
        sc = new Scanner(System.in);
    }

    public void iniciarJuego() {
        // Repartir cartas
        for (int i = 0; i < 7; i++) {
            humano.robar(baraja);
            computadora.robar(baraja);
        }

        cartaMesa = baraja.robarCarta();
        System.out.println("Carta inicial: " + cartaMesa);

        jugar();
    }

    private void jugar() {
        boolean turnoHumano = true;

        while (true) {
            if (turnoHumano) {
                turnoHumano();
            } else {
                turnoComputadora();
            }

            // Ganador
            if (humano.mano.isEmpty()) {
                System.out.println("¡Ganaste!");
                break;
            }

            if (computadora.mano.isEmpty()) {
                System.out.println("La computadora gana");
                break;
            }

            turnoHumano = !turnoHumano;
        }
    }

    private void turnoHumano() {
        System.out.println("\nTu turno");
        System.out.println("Carta en mesa: " + cartaMesa);

        humano.mostrarMano();

        System.out.println("Elige carta (R para robar): ");
        String input = sc.next();

        if (input.equalsIgnoreCase("R")) {
            humano.robar(baraja);
            return;
        }

        try {
            int opcion = Integer.parseInt(input);

            if (opcion >= 0 && opcion < humano.mano.size()) {
                Carta seleccionada = humano.mano.get(opcion);

                if (seleccionada.esValida(cartaMesa)) {
                    cartaMesa = seleccionada;
                    humano.mano.remove(opcion);
                } else {
                    System.out.println("Movimiento invalido");
                }
            } else {
                System.out.println("Opcion invalida");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida (usa numero o R)");
        }
    }

    private void turnoComputadora() {
        System.out.println("\nTurno de la computadora...");
        System.out.println("Carta en mesa: " + cartaMesa);

        boolean jugo = false;

        for (int i = 0; i < computadora.mano.size(); i++) {
            Carta carta = computadora.mano.get(i);

            if (carta.esValida(cartaMesa)) {
                System.out.println("Computadora juega: " + carta);
                cartaMesa = carta;
                computadora.mano.remove(i);
                jugo = true;
                break;
            }
        }

        if (!jugo) {
            System.out.println("Computadora roba carta");
            computadora.robar(baraja);
        }
    }
}