
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
    public Carta()
    {
        this.tipo = tipo;
        this.vida = 1.0;
        this.ataque = Math.random() * (1.0 - 0.6) + 0.6; //aleatorio entre 0.6 y 1.0
        this.defensa = Math.random() * (0.5 - 0.1) + 0.1; //aleatorio entre 0.1 y 0.5
        this.jugador = null;
    }

    
    /**
     * Escoge aleatoriamente un tipo de carta de un array con los diferentes tipos.
     * 
     * @return tipo de carta escogida aleatoriamente (agua, tierra o aire).
     */
    public String asignarTipo(int i){
       String[] tiposCarta = {"Agua", "Aire", "Tierra"}; 
       return tiposCarta[i]; //retorna un tipo de carta
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
        this.ataque = defensa;
    }
    
    public void setDefensa(double defensa){
        this.defensa = defensa;
    }
    
    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }
}