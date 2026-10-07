package uvgcardbattle.model;

public final class CartaEventoCampus extends Carta {
    private TipoEvento tipoEvento;
    private int magnitud;

    public CartaEventoCampus(int id, String nombre, int costoEnergia, String descripcion,
                             TipoEvento tipoEvento, int magnitud) {
        super(id, nombre, costoEnergia, descripcion);
        this.tipoEvento = tipoEvento;
        this.magnitud = magnitud;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador oponente, Tablero tablero) {
        String efecto;
        switch (tipoEvento) {
            case RECARGA -> {
                actual.modificarEnergia(magnitud);
                efecto = actual.getNombre() + " recupera " + magnitud + " de energía.";
            }
            case DESGASTE -> {
                actual.modificarEnergia(-magnitud);
                oponente.modificarEnergia(-magnitud);
                efecto = "Ambos jugadores pierden " + magnitud + " de energía.";
            }
            case INTERCAMBIO -> {
                int energiaActual = actual.getEnergia();
                actual.setEnergia(oponente.getEnergia());
                oponente.setEnergia(energiaActual);
                efecto = "Los jugadores intercambian su energía.";
            }
            default -> throw new IllegalStateException("Evento desconocido");
        }
        tablero.registrar("Evento: " + getNombre());
        return efecto;
    }

    public TipoEvento getTipoEvento() { return tipoEvento; }
    public int getMagnitud() { return magnitud; }

    @Override
    public String toString() {
        return "EVENTO {" + datosBasicos() + ", tipo=" + tipoEvento
                + ", magnitud=" + magnitud + "}";
    }
}
