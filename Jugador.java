package uvgcardbattle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Jugador {
    private final String nombre;
    private int energia;
    private final List<Integer> idsMano = new ArrayList<>();

    public Jugador(String nombre) { this(nombre, 10); }

    public Jugador(String nombre, int energiaInicial) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre obligatorio");
        this.nombre = nombre.trim();
        setEnergia(energiaInicial);
    }

    public void tomarCarta(int idCarta) { idsMano.add(idCarta); }
    public boolean tieneCarta(int idCarta) { return idsMano.contains(idCarta); }
    public boolean retirarCarta(int idCarta) { return idsMano.remove(Integer.valueOf(idCarta)); }
    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public List<Integer> getIdsMano() { return Collections.unmodifiableList(idsMano); }
    public void modificarEnergia(int cambio) { setEnergia(energia + cambio); }
    public void setEnergia(int energia) { this.energia = Math.max(0, energia); }

    @Override
    public String toString() {
        return nombre + " | energía=" + energia + " | cartas=" + idsMano.size();
    }
}
