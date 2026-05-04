
/**
 * Esta clase representa la carta del juego.
 * 
 * 
 * @author Bryan Morales, Maria Vargas, José Rojas 
 * @version 1.0
 */
public class Carta
{
    private double vida;
    private double ataque;
    private double defensa;
    private String tipo;
    private Jugador jugador; //referencia al jugador

    /**
     * Constructor defecto
     */
    public Carta(String tipo){
        this.tipo = tipo;
        this.vida = 1.0;
        this.ataque = Math.random() * (1.0 - 0.6) + 0.6; //aleatorio entre 0.6 y 1.0
        this.defensa = Math.random() * (0.5 - 0.1) + 0.1; //aleatorio entre 0.1 y 0.5
        this.jugador = null;
    }

    /**
     * Metodo para hacer daño a otra carta
     * Si la carta tiene poder puede realizar daño.
     * 
     * @param carta del oponente a cual aplica el daño.
     */
    public void atacar(Carta oponente){
        double vidaOponente = oponente.getVida();
        //daño en carta del mismo tipo
        if(vida > 0){
            vidaOponente -= vidaOponente *(ataque - oponente.getDefensa());    
            oponente.setVida(vidaOponente); // actualizar vida oponente real
        }
    }

    /**
     * Verificar si la carta puede afectar a la carta del oponente según el tipo.
     * 
     * @param carta que podrá ser afectada.
     * @return true, puede realizar daño.
     * @return false, indica que no puede hacer daño
     */
    public boolean afectaCarta(Carta oponente){
        boolean aireTierra = getTipo().equals("Aire") && oponente.getTipo().equals("Tierra"); // aire afecta tierra.
        boolean tierraAgua = getTipo().equals("Tierra") && oponente.getTipo().equals("Agua"); // tierra afecta agua.
        boolean aguaAire = getTipo().equals("Agua") && oponente.getTipo().equals("Aire"); // agua afecta aire

        return getTipo().equals(oponente.getTipo()) || aireTierra || tierraAgua || aguaAire;
    }

    // toString para pruebas de impresion de datos
    public String toString(){
        return String.format(" tipo: %s, vida: %.1f, ataque: %.1f, defensa: %.1f", tipo, vida, ataque, defensa);
    }

    // getters
    public String getTipo(){
        return tipo;
    }

    public double getVida(){
        return vida;
    }

    public double getAtaque(){
        return ataque;
    }

    public double getDefensa(){
        return defensa;
    }

    public Jugador getJugador(){
        return jugador;
    }

    // setters
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setVida(double vida){
        this.vida = vida;
    }

    public void setAtaque(double ataque){
        this.ataque = ataque;
    }

    public void setDefensa(double defensa){
        this.defensa = defensa;
    }

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }
}