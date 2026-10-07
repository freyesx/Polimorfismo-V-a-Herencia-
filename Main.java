package uvgcardbattle;

import uvgcardbattle.controller.JuegoController;
import uvgcardbattle.model.CatalogoCartas;
import uvgcardbattle.view.ConsolaView;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        new JuegoController(new CatalogoCartas(), new ConsolaView()).iniciar();
    }
}
