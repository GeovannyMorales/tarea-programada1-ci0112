/**
 * Representa una carta de poder del juego.
 * Cada carta tiene un tipo (Aire, Tierra o Agua), vida, ataque y defensa.
 */
public class Carta {

    private double vida;
    private double ataque;
    private double defensa;
    private String tipo;
    private Jugador jugador;

    /**
     * Constructor para carta con tipo específico (usado por Jugador).
     * @param tipo el tipo de la carta: "Aire", "Tierra" o "Agua"
     */
    public Carta(String tipo) {
        this.tipo = tipo;
        this.vida = 1.0;
        this.ataque = Math.random() * (1.0 - 0.6) + 0.6;
        this.defensa = Math.random() * (0.5 - 0.1) + 0.1;
        this.jugador = null;
    }

    /**
     * Constructor para carta con tipo aleatorio (usado por Oponente).
     */
    public Carta() {
        String[] tipos = {"Agua", "Aire", "Tierra"};
        this.tipo = tipos[(int) (Math.random() * tipos.length)];
        this.vida = 1.0;
        this.ataque = Math.random() * (1.0 - 0.6) + 0.6;
        this.defensa = Math.random() * (0.5 - 0.1) + 0.1;
        this.jugador = null;
    }

    /**
     * Esta carta ataca a la carta oponente.
     * Aire vence a Tierra, Tierra vence a Agua, Agua vence a Aire.
     * Si son del mismo tipo, ambas pierden vida.
     * @param oponente la carta que recibe el ataque
     */
    public void atacar(Carta oponente) {
        if (this.estaMuerta() || oponente.estaMuerta()) return;

        boolean hagoDanio = false;
        boolean reciboDanio = false;

        if (this.tipo.equals("Aire") && oponente.tipo.equals("Tierra")) {
            hagoDanio = true;
        } else if (this.tipo.equals("Tierra") && oponente.tipo.equals("Agua")) {
            hagoDanio = true;
        } else if (this.tipo.equals("Agua") && oponente.tipo.equals("Aire")) {
            hagoDanio = true;
        } else if (this.tipo.equals(oponente.tipo)) {
            hagoDanio = true;
            reciboDanio = true;
        } else {
            reciboDanio = true;
        }

        if (hagoDanio) {
            double danio = this.ataque - oponente.defensa;
            if (danio > 0) oponente.recibirDanio(danio);
        }
        if (reciboDanio) {
            double danio = oponente.ataque - this.defensa;
            if (danio > 0) this.recibirDanio(danio);
        }
    }

    /**
     * Reduce la vida de la carta según el daño recibido.
     * @param cantidad el daño recibido
     */
    public void recibirDanio(double cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
    }

    /**
     * Indica si la carta está muerta (vida menor o igual a 0).
     * @return true si la carta está muerta
     */
    public boolean estaMuerta() {
        return vida <= 0;
    }

    /**
     * Devuelve una representación en texto de la carta con todos sus atributos.
     * @return String con tipo, vida, ataque y defensa
     */
    public String toString() {
        return String.format("Tipo: %-6s | Vida: %5.1f%% | Ataque: %.2f | Defensa: %.2f",
                tipo, vida * 100, ataque, defensa);
    }

    /** @return el tipo de la carta */
    public String getTipo() { return tipo; }

    /** @return la vida actual de la carta */
    public double getVida() { return vida; }

    /** @return el poder de ataque de la carta */
    public double getAtaque() { return ataque; }

    /** @return el poder de defensa de la carta */
    public double getDefensa() { return defensa; }

    /** @return el jugador dueño de esta carta */
    public Jugador getJugador() { return jugador; }

    /**
     * Asigna el jugador dueño de esta carta.
     * @param jugador el jugador que será dueño de la carta
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
