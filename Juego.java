package uvgcardbattle.model;

public final class Juego {
    private final CatalogoCartas catalogo;
    private final Jugador[] jugadores;
    private final Tablero tablero = new Tablero();
    private int indiceActual;

    public Juego(CatalogoCartas catalogo, String nombre1, String nombre2) {
        this.catalogo = catalogo;
        this.jugadores = new Jugador[]{new Jugador(nombre1), new Jugador(nombre2)};
        for (int i = 0; i < 3; i++) {
            tomarCarta();
            cambiarJugadorSinTurno();
            tomarCarta();
            cambiarJugadorSinTurno();
        }
    }

    public Jugador getActual() { return jugadores[indiceActual]; }
    public Jugador getOponente() { return jugadores[1 - indiceActual]; }
    public Tablero getTablero() { return tablero; }

    public Carta tomarCarta() {
        Carta carta = catalogo.cartaAleatoria();
        getActual().tomarCarta(carta.getId());
        return carta;
    }

    public String jugarCarta(int idCarta) {
        Jugador actual = getActual();
        if (!actual.tieneCarta(idCarta)) return "La carta no está en la mano.";
        Carta carta = catalogo.buscar(idCarta).orElse(null);
        if (carta == null) return "La carta no existe.";
        if (actual.getEnergia() < carta.getCostoEnergia()) return "No hay energía suficiente.";
        actual.modificarEnergia(-carta.getCostoEnergia());
        actual.retirarCarta(idCarta);
        return carta.jugarCarta(actual, getOponente(), tablero);
    }

    // Sobrecarga: permite jugar por nombre además de hacerlo por ID.
    public String jugarCarta(String nombre) {
        Carta carta = catalogo.buscar(nombre).orElse(null);
        return carta == null ? "La carta no existe." : jugarCarta(carta.getId());
    }

    public void finalizarTurno() {
        indiceActual = 1 - indiceActual;
        getActual().modificarEnergia(2);
        tablero.siguienteTurno();
    }

    public boolean termino() { return getActual().getEnergia() == 0 || getOponente().getEnergia() == 0; }
    public Jugador ganador() {
        if (!termino()) return null;
        return getActual().getEnergia() > getOponente().getEnergia() ? getActual() : getOponente();
    }
    private void cambiarJugadorSinTurno() { indiceActual = 1 - indiceActual; }
}
