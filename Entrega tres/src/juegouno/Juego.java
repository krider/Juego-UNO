package juegouno;

import java.util.*;

public class Juego {

    Baraja baraja = new Baraja();
    ArrayList<Jugador> jugadores = new ArrayList<>();
    int turno = 0;
    int direccion = 1;

    Carta cartaMesa;
    ArrayList<Carta> descarte = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void iniciarJuego() {

        System.out.print("Ingresa tu nombre: ");
        String nombre = sc.nextLine();

        jugadores.add(new Jugador(nombre));
        jugadores.add(new Jugador("Pepe"));
        jugadores.add(new Jugador("Toña"));
        jugadores.add(new Jugador("Mari"));

        // Repartir cartas
        for (int i = 0; i < 7; i++)
            for (Jugador j : jugadores)
                j.robar(baraja);

        // Carta inicial válida
        do {
            cartaMesa = baraja.robarCarta();
        } while (cartaMesa.tipo.equals("COMODIN") || cartaMesa.tipo.equals("ROBA4"));

        while (true) {

            mostrarEstado();

            Jugador actual = jugadores.get(turno);

            if (turno == 0)
                turnoHumano(actual);
            else
                turnoIA(actual);

            // Ganador
            if (actual.mano.isEmpty()) {
                System.out.println("🏆 " + actual.nombre + " ha ganado!");
                break;
            }

            avanzarTurno();
        }
    }

    private void mostrarEstado() {

        System.out.println("\n==============================");
        System.out.println("Carta mesa: " + cartaMesa);

        for (Jugador j : jugadores)
            System.out.println(j.nombre + ": " + j.mano.size() + " cartas");

        System.out.println("==============================");
    }

    private void avanzarTurno() {
        turno = (turno + direccion + jugadores.size()) % jugadores.size();
    }

    private Carta robarSegura() {
        Carta c = baraja.robarCarta();
        if (c == null) {
            baraja.cartas.addAll(descarte);
            descarte.clear();
            baraja.barajar();
            c = baraja.robarCarta();
        }
        return c;
    }

    // JUGADOR HUMANO 
    private void turnoHumano(Jugador j) {

        while (true) {

            j.mostrarManoHorizontal();
            System.out.print("Elige carta o R: ");
            String input = sc.next();

            if (input.equalsIgnoreCase("R")) {
                Carta c = robarSegura();
                j.mano.add(c);
                System.out.println("Robaste: " + c);
                return;
            }

            try {
                int i = Integer.parseInt(input);
                Carta c = j.mano.get(i);

                System.out.println("Intentas jugar: " + c);

                if (c.esValida(cartaMesa)) {
                    j.mano.remove(i);
                    jugarCarta(j, c);
                    return;
                } else {
                    System.out.println("Movimiento inválido");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida");
            }
        }
    }

    // COMPUTADORA (IA)
    private void turnoIA(Jugador j) {

        System.out.println(j.nombre + " juega...");

        for (Carta c : j.mano) {
            if (c.esValida(cartaMesa)) {
                j.mano.remove(c);
                jugarCarta(j, c);
                return;
            }
        }

        Carta c = robarSegura();
        j.mano.add(c);

        if (c.esValida(cartaMesa)) {
            j.mano.remove(c);
            jugarCarta(j, c);
        }
    }

    // JUGAR CARTA 
    private void jugarCarta(Jugador j, Carta c) {

        descarte.add(cartaMesa);
        cartaMesa = c;

        System.out.println(j.nombre + " juega: " + c);

       
        if (j.mano.size() == 1) {
            System.out.println("🔥 ¡" + j.nombre.toUpperCase() + " DICE UNO! ");
        }

        aplicarEfecto(j, c);
    }

    // EFECTOS 
    private void aplicarEfecto(Jugador j, Carta c) {

        switch (c.tipo) {

            case "SALTO":
                avanzarTurno();
                break;

            case "REVERSA":
                direccion *= -1;
                break;

            case "ROBA2":
                robarCartas(2);
                avanzarTurno();
                break;

            case "ROBA4":
                robarCartas(4);
                cambiarColor(j, c);
                avanzarTurno();
                break;

            case "COMODIN":
                cambiarColor(j, c);
                break;
        }
    }

    private void robarCartas(int n) {
        Jugador siguiente = jugadores.get((turno + direccion + jugadores.size()) % jugadores.size());

        for (int i = 0; i < n; i++)
            siguiente.robar(baraja);

        System.out.println("Aviso: " + siguiente.nombre + " roba " + n + " cartas");
    }

    private void cambiarColor(Jugador j, Carta c) {

        if (turno == 0) {
            System.out.print("Elige color (Rojo/Azul/Verde/Amarillo): ");
            c.color = sc.next();
        } else {
            String[] colores = {"Rojo", "Azul", "Verde", "Amarillo"};
            c.color = colores[new Random().nextInt(4)];
            System.out.println(j.nombre + " cambia a " + c.color);
        }
    }
}