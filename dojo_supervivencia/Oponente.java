/**
 * Representa un oponente del juego.
 * Cada oponente tiene tres cartas con tipos aleatorios y un nivel de intensidad
 * que determina cuánta energía consume cada combo del equipo.
 */
public class Oponente {

    private Carta[] cartas;
    private double intensidad;

    /**
     * Crea un oponente con la intensidad indicada y tres cartas aleatorias.
     * @param intensidad el nivel de intensidad del oponente (0.2, 0.3 o 0.4)
     */
    public Oponente(double intensidad) {
        this.intensidad = intensidad;
        this.cartas = new Carta[3];
        for (int i = 0; i < 3; i++) {
            cartas[i] = new Carta();
        }    
    }

    /**
     * Reduce la intensidad del oponente a la mitad.
     */
    public void reducirIntensidad() {
        this.intensidad /= 2.0;
    }

    /**
     * Indica si el oponente fue derrotado (todas sus cartas no tienen vida).
     * @return true si el oponente está derrotado
     */
    public boolean estaDerrotado() {
        for (Carta c : cartas){
            if (!c.estaMuerta()){
                return false;
            }    
        }
        return true;
    }

    /** 
     * @return el arreglo de cartas del oponente
     */
    public Carta[] getCartas() {
        return cartas;
    }

    /**
     * @return el nivel de intensidad actual del oponente
     */
    public double getIntensidad() {
        return intensidad;
    }
}
