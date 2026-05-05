/**
 * Representa un jugador del equipo.
 * Cada jugador tiene tres cartas: Aire, Tierra y Agua.
 */
public class Jugador {

    private String nombre;
    private Carta[] cartas;

    /**
     * Crea un jugador con su nombre y le asigna tres cartas (Aire, Tierra, Agua).
     * @param nombre el nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new Carta[3];

        cartas[0] = new Carta("Aire");
        cartas[1] = new Carta("Tierra");
        cartas[2] = new Carta("Agua");

        for (Carta c : cartas) c.setJugador(this);
    }

    /**
     * Indica si el jugador sigue activo (tiene al menos una carta viva).
     * @return true si el jugador está activo
     */
    public boolean estaActivo() {
        for (Carta c : cartas) if (!c.estaMuerta()) return true;
        return false;
    }

    /**
     * Devuelve la carta del tipo indicado si existe y está viva.
     * @param tipo el tipo de carta buscada
     * @return la carta si existe y está viva, null si no
     */
    public Carta getCartaPorTipo(String tipo) {
        for (Carta c : cartas)
            if (c.getTipo().equals(tipo) && !c.estaMuerta()) return c;
        return null;
    }

    /**
     * Muestra el estado del jugador durante el juego (solo vida de sus cartas).
     * @param ui la interfaz que se usa para mostrar el mensaje
     */
    public void mostrarEstadoJuego(Interfaz ui) {
        ui.mostrar(String.format("  %s | %s", nombre, estaActivo() ? "Activo" : "Derrotado"));
        ui.mostrar(String.format("    Vida: Aire %.1f%%  Tierra %.1f%%  Agua %.1f%%",
                cartas[0].getVida() * 100,
                cartas[1].getVida() * 100,
                cartas[2].getVida() * 100));
    }

    /**
     * Muestra el estado completo del jugador al final del juego (ataque y defensa incluidos).
     * @param ui la interfaz que se usa para mostrar el mensaje
     */
    public void mostrarEstadoCompleto(Interfaz ui) {
        ui.mostrar(String.format("  Jugador: %s | %s", nombre, estaActivo() ? "Activo" : "Derrotado"));
        for (Carta c : cartas) ui.mostrar("    " + c.toString());
    }

    /** @return el arreglo de cartas del jugador */
    public Carta[] getCartas() { return cartas; }

    /** @return el nombre del jugador */
    public String getNombre() { return nombre; }
}
