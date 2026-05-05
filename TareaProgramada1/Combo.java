/**
 * Representa un combo de ataque del equipo contra un oponente.
 * Un combo enfrenta tres cartas del equipo contra las tres cartas del oponente
 * durante una cantidad determinada de ataques.
 */
public class Combo {

    private Carta[] cartasEquipo;
    private Carta[] cartasOponente;
    private int cantidadAtaques;

    /**
     * Crea un combo con las cartas seleccionadas y la cantidad de ataques.
     * @param cartasEquipo las tres cartas del equipo para el combo
     * @param cartasOponente las tres cartas del oponente
     * @param cantidadAtaques la cantidad de veces que se repite el enfrentamiento
     */
    public Combo(Carta[] cartasEquipo, Carta[] cartasOponente, int cantidadAtaques) {
        this.cartasEquipo = cartasEquipo;
        this.cartasOponente = cartasOponente;
        this.cantidadAtaques = cantidadAtaques;
    }

    /**
     * Ejecuta el combo enfrentando cada carta del equipo contra su carta oponente
     * la cantidad de ataques indicada.
     */
    public void ejecutar() {
        for (int ataque = 0; ataque < cantidadAtaques; ataque++) {
            for (int j = 0; j < 3; j++) {
                Carta cEquipo = cartasEquipo[j];
                Carta cOponente = cartasOponente[j];
                if (!cEquipo.estaMuerta() && !cOponente.estaMuerta()) {
                    cEquipo.atacar(cOponente);
                }
            }
        }
    }

    /**
     * Valida que el combo sea válido.
     * Para ser válido, las tres cartas deben existir y estar vivas,
     * y cada jugador activo debe aportar al menos una carta.
     * @param equipo el arreglo de jugadores del equipo
     * @return true si el combo es válido
     */
    public boolean esValido(Jugador[] equipo) {
        for (Carta c : cartasEquipo)
            if (c == null || c.estaMuerta()) return false;

        for (Jugador j : equipo) {
            if (!j.estaActivo()) continue;
            boolean aporta = false;
            for (Carta c : cartasEquipo)
                if (c.getJugador() == j) { aporta = true; break; }
            if (!aporta) return false;
        }
        return true;
    }
}
