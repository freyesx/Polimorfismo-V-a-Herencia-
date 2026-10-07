package uvgcardbattle.model;

import java.util.Objects;

public abstract class Carta {
    private final int id;
    private String nombre;
    private int costoEnergia;
    private String descripcion;

    protected Carta(int id, String nombre, int costoEnergia, String descripcion) {
        if (id <= 0) throw new IllegalArgumentException("El ID debe ser positivo");
        setNombre(nombre);
        setCostoEnergia(costoEnergia);
        setDescripcion(descripcion);
        this.id = id;
    }

    public abstract String jugarCarta(Jugador jugadorActual, Jugador oponente, Tablero tablero);

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCostoEnergia() { return costoEnergia; }
    public String getDescripcion() { return descripcion; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre obligatorio");
        this.nombre = nombre.trim();
    }

    public void setCostoEnergia(int costoEnergia) {
        if (costoEnergia < 0) throw new IllegalArgumentException("El costo no puede ser negativo");
        this.costoEnergia = costoEnergia;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) throw new IllegalArgumentException("Descripción obligatoria");
        this.descripcion = descripcion.trim();
    }

    protected String datosBasicos() {
        return "ID=" + id + ", nombre='" + nombre + "', costo=" + costoEnergia
                + ", descripción='" + descripcion + "'";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Carta otra)) return false;
        return id == otra.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public abstract String toString();
}
