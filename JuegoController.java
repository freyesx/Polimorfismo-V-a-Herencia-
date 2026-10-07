package uvgcardbattle.controller;

import uvgcardbattle.model.Carta;
import uvgcardbattle.model.CatalogoCartas;
import uvgcardbattle.model.Juego;
import uvgcardbattle.view.ConsolaView;

import java.util.List;

public final class JuegoController {
    private final CatalogoCartas catalogo;
    private final ConsolaView vista;

    public JuegoController(CatalogoCartas catalogo, ConsolaView vista) {
        this.catalogo = catalogo;
        this.vista = vista;
    }

    public void iniciar() {
        catalogo.cargarCartasIniciales();
        vista.mostrarTitulo();
        int opcion;
        do {
            vista.mostrarMenuPrincipal();
            opcion = vista.leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> vista.mostrarCartas(catalogo.listar());
                case 2 -> buscarCarta();
                case 3 -> { catalogo.ordenarPorCosto(); vista.mostrar("Mazo ordenado por costo."); }
                case 4 -> iniciarPartida();
                case 0 -> vista.mostrar("Gracias por jugar.");
                default -> vista.mostrar("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void buscarCarta() {
        int tipo = vista.leerEntero("Buscar por 1.ID o 2.Nombre: ");
        Carta resultado = tipo == 1
                ? catalogo.buscar(vista.leerEntero("ID: ")).orElse(null)
                : catalogo.buscar(vista.leerTexto("Nombre: ")).orElse(null);
        vista.mostrar(resultado == null ? "Carta no encontrada." : resultado.toString());
    }

    private void iniciarPartida() {
        String nombre1 = vista.leerTexto("Nombre del jugador 1: ");
        String nombre2 = vista.leerTexto("Nombre del jugador 2: ");
        Juego juego = new Juego(catalogo, nombre1, nombre2);
        boolean salir = false;

        while (!salir && !juego.termino()) {
            vista.mostrarEstado(juego.getActual(), juego.getOponente(), juego.getTablero().getTurno());
            mostrarMano(juego);
            vista.mostrarMenuTurno();
            int opcion = vista.leerEntero("Acción: ");
            switch (opcion) {
                case 1 -> vista.mostrar(juego.jugarCarta(vista.leerEntero("ID de la carta: ")));
                case 2 -> vista.mostrar("Tomaste: " + juego.tomarCarta().getNombre());
                case 3 -> vista.mostrar("Turno pasado.");
                case 0 -> salir = true;
                default -> { vista.mostrar("Acción inválida."); continue; }
            }
            if (!salir) juego.finalizarTurno();
        }
        if (juego.termino()) vista.mostrar("Ganador: " + juego.ganador().getNombre());
        else vista.mostrar("Partida finalizada.");
    }

    private void mostrarMano(Juego juego) {
        List<Carta> mano = juego.getActual().getIdsMano().stream()
                .map(id -> catalogo.buscar(id).orElse(null))
                .filter(carta -> carta != null)
                .toList();
        vista.mostrar("Cartas en mano:");
        vista.mostrarCartas(mano);
    }
}
