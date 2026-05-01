
/**
 * Clase que corresponde al jugador
 * 
 * @author Bryan Morales, Maria Vargas, José Rojas
 * @version 1.0
 */
public class Jugador
{
    private Carta[] cartasJugador;
    private String nombre;
    
    /**
     * Constructor por defecto.
     */
    public Jugador()
    {
        this.cartasJugador = cartasJugador;
        this.nombre = nombre;
    }

    /**
     * Metodo que crea las cartas que obtendra el jugador
     * 
     * 
     */
    public void recibirCarta(){
    
        cartasJugador = new Carta[3]; // "mazo" con 3 cartas para el jugador.
        
        for(int i = 0; i < cartasJugador.length; i++){
            cartasJugador[i] = new Carta();
            cartasJugador[i].setTipo(cartasJugador[i].asignarTipo(i)); // por cada iteracion recibe un tipo de carta diferente.
        }
    }
    
    /**
     * Metodo toString para imprimir datos del jugador(nombre y cartas asignadas).
     * 
     * @return un string con el nombre del jugador y la información de sus cartas.
     */
    public String toString(){
        String datosJugador = " ";
        int numCarta = 1; // etiqueta número de carta
        
        for(int i = 0; i < cartasJugador.length; i++){
            datosJugador += "\nCarta N°: " + numCarta + cartasJugador[i].toString();
            numCarta += 1;
        }
        
        return "Nombre: " + nombre + datosJugador;
    }
    
    
    
    //metodo para ver si jugador sigue vivo?
    
    //setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}