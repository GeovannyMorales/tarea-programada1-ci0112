// clase que maneja los combos
public class Combo {

    private Carta[] cartasEquipo;
    private Carta[] cartasOponente;
    private int cantidadAtaques;

    public Combo(Carta[] cartasEquipo, Carta[] cartasOponente, int cantidadAtaques) {
        this.cartasEquipo = cartasEquipo;
        this.cartasOponente = cartasOponente;
        this.cantidadAtaques = cantidadAtaques;
    }

    // ejecuta el combo
    public void ejecutar() {

        for (int i = 0; i < cantidadAtaques; i++) {

            for (int j = 0; j < 3; j++) {

                Carta cj = cartasEquipo[j];
                Carta co = cartasOponente[j];

                if (!cj.estaMuerta() && !co.estaMuerta()) {
                    cj.atacar(co);
                }
            }
        }
    }

    // valida el combo
    public boolean esValido(Jugador[] jugadores) {

        for (Jugador j : jugadores) {

            if (j.estaActivo()) {

                boolean aporto = false;

                for (Carta c : cartasEquipo) {
                    if (c.getJugador() == j && !c.estaMuerta()) {
                        aporto = true;
                        break;
                    }
                }

                if (!aporto) return false;
            }
        }

        return true;
    }
}