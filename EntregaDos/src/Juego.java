package juegouno;
import java.util.Scanner;
public class Juego {

    Baraja baraja = new Baraja();
    Jugador humano = new Jugador("Jugador");
    Jugador computadora = new Jugador("Computadora");
    Carta cartaMesa;
    boolean turnoHumano = true;
    boolean saltarTurno = false;
    Scanner sc = new Scanner(System.in);

    public void iniciarJuego() {

        // Repartir  las 7 cartas
        for (int i = 0; i < 7; i++) {
            humano.robar(baraja);
            computadora.robar(baraja);
        }

        cartaMesa = baraja.robarCarta();
        System.out.println("Carta inicial: " + cartaMesa);
        while (true) {
            if (turnoHumano) {
                if (!saltarTurno)
                    turnoHumano();
                else {
                    System.out.println("Turno saltado");
                    saltarTurno = false;
                }
            } else {
                if (!saltarTurno)
                    turnoComputadora();
                else {
                    System.out.println("Turno computadora saltado");
                    saltarTurno = false;
                }
            }
            // Verificar ganador
            if (humano.mano.isEmpty()) {
                System.out.println("GANASTE MUY BIEN");
                break;
            }
            if (computadora.mano.isEmpty()) {
                System.out.println("GANA LA COMPUTADORA");
                break;
            }
            turnoHumano = !turnoHumano;
        }
    }
    
    // TURNO HUMANO(JUGADOR)
    private void turnoHumano() {
        while (true) {

            System.out.println("\nTu turno — Carta mesa: " + cartaMesa);
            humano.mostrarMano();
            System.out.println("Elige carta o R para robar:");
            String input = sc.next();
            if (input.equalsIgnoreCase("R")) {
                Carta robada = baraja.robarCarta();
                humano.mano.add(robada);
                System.out.println("Robaste: " + robada);
                if (robada.esValida(cartaMesa)) {
                    System.out.println("Se juega automaticamente");
                    humano.mano.remove(robada);
                    jugarCarta(humano, robada, true);
                }
                return;
            }
            int i = Integer.parseInt(input);
            if (i < 0 || i >= humano.mano.size()) {
                System.out.println("Indice invalido");
                continue;
            }

            Carta c = humano.mano.get(i);
            if (c.esValida(cartaMesa)) {
                humano.mano.remove(i);
                jugarCarta(humano, c, true);
                return;
            } else {
                System.out.println("Movimiento invalido — intenta con otra carta");
            }
        }
    }

    // TURNO COMPUTADORA
    private void turnoComputadora() {

        System.out.println("\nTurno computadora — Carta mesa: " + cartaMesa);
        for (Carta c : computadora.mano) {
            if (c.esValida(cartaMesa)) {
                System.out.println("Computadora juega: " + c);
                computadora.mano.remove(c);
                jugarCarta(computadora, c, false);
                return;
            }
        }
        
        System.out.println("Computadora roba");
        Carta robada = baraja.robarCarta();
        computadora.mano.add(robada);
        if (robada.esValida(cartaMesa)) {
            System.out.println("Computadora juega: " + robada);
            computadora.mano.remove(robada);
            jugarCarta(computadora, robada, false);
        }
    }

    // JUGAR CARTA
    private void jugarCarta(Jugador jugador, Carta c, boolean esHumano) {

        cartaMesa = c;

        // SE APLICA LA REGLA DE DECIR "UNO"
        if (jugador.mano.size() == 1) {

            if (esHumano) {

                System.out.println("¡Te queda UNA carta!");
                System.out.println("¿Decir UNO? (S/N)");
                String resp = sc.next();
                if (resp.equalsIgnoreCase("S")) {
                    System.out.println("¡UNO!");
                } else {

                    System.out.println("No dijiste UNO — Robas 2 cartas");

                    jugador.robar(baraja);
                    jugador.robar(baraja);
                }
            } else {

                // Computadora
                if (Math.random() < 0.7) {
                    System.out.println("Computadora dice: ¡UNO! ");
                } else {

                    System.out.println("Computadora olvidO decir UNO ");
                    System.out.println("Computadora roba 2 cartas");
                    jugador.robar(baraja);
                    jugador.robar(baraja);
                }
            }
        }
        aplicarEfecto(c, esHumano);
    }
    // EFECTOS DE LOS COMODINES 
    private void aplicarEfecto(Carta c, boolean esHumano) {

        switch (c.tipo) {

            case "SALTO":
                System.out.println("Turno del oponente saltado");
                saltarTurno = true;
                break;

            case "REVERSA":
                // En  el juegoslos 2 jugadores en REVERSA = SALTO
                System.out.println("REVERSA — turno del oponente saltado");
                saltarTurno = true;
                break;

            case "ROBA2":
                System.out.println("El oponente roba 2 cartas");

                for (int i = 0; i < 2; i++)
                    if (esHumano) computadora.robar(baraja);
                    else humano.robar(baraja);

                saltarTurno = true;
                break;

            case "ROBA4":
                System.out.println("El oponente roba 4 cartas");

                for (int i = 0; i < 4; i++)
                    if (esHumano) computadora.robar(baraja);
                    else humano.robar(baraja);

                cambiarColor(c, esHumano);
                saltarTurno = true;
                break;

            case "COMODIN":
                cambiarColor(c, esHumano);
                break;
        }
    }

    // CAMBIAR COLOR
    private void cambiarColor(Carta c, boolean esHumano) {
        String nuevoColor;
        if (esHumano) {

            System.out.println("Elige color: Rojo, Azul, Verde, Amarillo");
            nuevoColor = sc.next();

        } else {

            String[] colores = { "Rojo", "Azul", "Verde", "Amarillo" };
            nuevoColor = colores[(int) (Math.random() * 4)];

            System.out.println("Computadora cambia a " + nuevoColor);
        }

        c.color = nuevoColor;
    }
}