package uvgcardbattle.model;

public final class CartaCatedratico extends Carta {
    private String departamento;
    private int llamadasAtencion;
    private int tiempoAtencion;

    public CartaCatedratico(int id, String nombre, int costoEnergia, String descripcion,
                            String departamento, int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costoEnergia, descripcion);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String jugarCarta(Jugador actual, Jugador oponente, Tablero tablero) {
        oponente.modificarEnergia(-llamadasAtencion);
        actual.modificarEnergia(tiempoAtencion);
        tablero.registrar(getNombre() + " llamó la atención a " + oponente.getNombre());
        return oponente.getNombre() + " pierde " + llamadasAtencion
                + " de energía y " + actual.getNombre() + " recupera " + tiempoAtencion + ".";
    }

    public String getDepartamento() { return departamento; }
    public int getLlamadasAtencion() { return llamadasAtencion; }
    public int getTiempoAtencion() { return tiempoAtencion; }

    @Override
    public String toString() {
        return "CATEDRÁTICO {" + datosBasicos() + ", departamento='" + departamento
                + "', llamadas=" + llamadasAtencion + ", atención=" + tiempoAtencion + "}";
    }
}
