package uvgcardbattle.model;

public final class CartaCurso extends Carta {
    private int creditos;
    private int dificultad;

    public CartaCurso(int id, String nombre, int costoEnergia, String descripcion,
                      int creditos, int dificultad) {
        super(id, nombre, costoEnergia, descripcion);
        this.creditos = creditos;
        this.dificultad = dificultad;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador oponente, Tablero tablero) {
        actual.modificarEnergia(creditos);
        oponente.modificarEnergia(-dificultad);
        tablero.agregarCreditos(actual, creditos);
        tablero.registrar(actual.getNombre() + " cursó " + getNombre());
        return actual.getNombre() + " gana " + creditos + " de energía y "
                + oponente.getNombre() + " pierde " + dificultad + ".";
    }

    public int getCreditos() { return creditos; }
    public int getDificultad() { return dificultad; }

    @Override
    public String toString() {
        return "CURSO {" + datosBasicos() + ", créditos=" + creditos
                + ", dificultad=" + dificultad + "}";
    }
}
