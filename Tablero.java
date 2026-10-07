package uvgcardbattle.model;

import java.util.HashMap;
import java.util.Map;

public final class Tablero {
    private int turno = 1;
    private String ultimoEvento = "La partida todavía no inicia";
    private final Map<String, Integer> creditos = new HashMap<>();

    public void siguienteTurno() { turno++; }
    public int getTurno() { return turno; }
    public String getUltimoEvento() { return ultimoEvento; }
    public void registrar(String evento) { ultimoEvento = evento; }
    public void agregarCreditos(Jugador jugador, int cantidad) {
        creditos.merge(jugador.getNombre(), cantidad, Integer::sum);
    }
    public int getCreditos(Jugador jugador) { return creditos.getOrDefault(jugador.getNombre(), 0); }
}
