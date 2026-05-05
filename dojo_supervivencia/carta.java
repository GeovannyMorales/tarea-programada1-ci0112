// clase que representa una carta del juego
public class Carta {

    private double vida;
    private double ataque;
    private double defensa;
    private String tipo;
    private Jugador jugador;

    // constructor
    public Carta(String tipo) {
        this.tipo = tipo;
        this.vida = 1.0;
        this.ataque = Math.random() * (1.0 - 0.6) + 0.6;
        this.defensa = Math.random() * (0.5 - 0.1) + 0.1;
        this.jugador = null;
    }

    // metodo de ataque entre cartas
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

    // reduce vida
    public void recibirDanio(double cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
    }

    // verifica si esta muerta
    public boolean estaMuerta() {
        return vida <= 0;
    }

    public String toString() {
        return String.format(" tipo: %s, vida: %.2f, ataque: %.2f, defensa: %.2f",
                tipo, vida, ataque, defensa);
    }

    public String getTipo() { return tipo; }
    public double getVida() { return vida; }
    public double getAtaque() { return ataque; }
    public double getDefensa() { return defensa; }
    public Jugador getJugador() { return jugador; }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}