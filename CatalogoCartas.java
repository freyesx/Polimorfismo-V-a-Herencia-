package uvgcardbattle.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class CatalogoCartas {
    // Única lista polimórfica que almacena todos los tipos de cartas.
    private final List<Carta> cartas = new ArrayList<>();
    private final Random random;

    public CatalogoCartas() { this(new Random()); }
    public CatalogoCartas(Random random) { this.random = random; }

    public void cargarCartasIniciales() {
        cartas.clear();
        cartas.add(new CartaCatedratico(1, "Profe de POO", 3, "Explica herencia", "Computación", 2, 1));
        cartas.add(new CartaCurso(2, "Programación Orientada a Objetos", 4, "Curso de diseño", 4, 2));
        cartas.add(new CartaEventoCampus(3, "Semana de Parciales", 2, "Cansancio general", TipoEvento.DESGASTE, 2));
        cartas.add(new CartaCatedratico(4, "Profe de Cálculo", 4, "Domina las derivadas", "Matemática", 3, 1));
        cartas.add(new CartaCurso(5, "Algoritmos", 3, "Estructuras y eficiencia", 3, 2));
        cartas.add(new CartaEventoCampus(6, "Feria de Clubes", 1, "Una pausa académica", TipoEvento.RECARGA, 3));
        cartas.add(new CartaCatedratico(7, "Profe de Física", 5, "Controla el laboratorio", "Física", 4, 2));
        cartas.add(new CartaCurso(8, "Bases de Datos", 3, "Consultas y modelos", 3, 1));
        cartas.add(new CartaEventoCampus(9, "Cambio de Sección", 2, "Cambia el ritmo", TipoEvento.INTERCAMBIO, 0));
        cartas.add(new CartaCurso(10, "Cálculo 2", 5, "Integrales y series", 5, 3));
        cartas.add(new CartaCatedratico(11, "Profe de Diseño", 2, "Retroalimentación creativa", "Diseño", 1, 2));
        cartas.add(new CartaEventoCampus(12, "Semana de Bienestar", 1, "Recuperación estudiantil", TipoEvento.RECARGA, 4));
        mezclar();
    }

    public Optional<Carta> buscar(int id) {
        return cartas.stream().filter(carta -> carta.getId() == id).findFirst();
    }

    public Optional<Carta> buscar(String nombre) {
        String consulta = nombre == null ? "" : nombre.trim().toLowerCase();
        return cartas.stream().filter(carta -> carta.getNombre().toLowerCase().contains(consulta)).findFirst();
    }

    public void ordenarPorCosto() {
        cartas.sort(Comparator.comparingInt(Carta::getCostoEnergia).thenComparing(Carta::getNombre));
    }

    public void mezclar() { java.util.Collections.shuffle(cartas, random); }
    public List<Carta> listar() { return List.copyOf(cartas); }
    public int cantidad() { return cartas.size(); }
    public Carta cartaAleatoria() { return cartas.get(random.nextInt(cartas.size())); }
}
