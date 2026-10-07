package uvgcardbattle.view;

import uvgcardbattle.model.Carta;
import uvgcardbattle.model.Jugador;

import java.util.List;
import java.util.Scanner;

public final class ConsolaView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarTitulo() { mostrar("\n=== UVG CARD BATTLE ==="); }
    public void mostrarMenuPrincipal() {
        mostrar("\n1. Listar cartas\n2. Buscar carta\n3. Ordenar por energía\n4. Jugar\n0. Salir");
    }
    public void mostrarMenuTurno() { mostrar("1. Jugar carta\n2. Tomar carta\n3. Pasar turno\n0. Terminar partida"); }
    public void mostrar(String mensaje) { System.out.println(mensaje); }
    public String leerTexto(String mensaje) { System.out.print(mensaje); return scanner.nextLine().trim(); }
    public int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException e) { mostrar("Ingrese un número válido."); }
        }
    }
    public void mostrarCartas(List<Carta> cartas) {
        if (cartas.isEmpty()) mostrar("No hay cartas.");
        else cartas.forEach(carta -> mostrar(carta.toString()));
    }
    public void mostrarEstado(Jugador actual, Jugador oponente, int turno) {
        mostrar("\nTurno " + turno + " | Juega: " + actual.getNombre());
        mostrar(actual.toString());
        mostrar(oponente.toString());
    }
}
