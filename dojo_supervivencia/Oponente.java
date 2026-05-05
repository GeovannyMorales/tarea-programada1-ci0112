
/**
 * Esta clase pertenece al Oponente
 * 
 * @author Bryan Morales, Maria Vargas, José Rojas 
 * @version 1.0
 */
public class Oponente
{
    private Carta[] cartasOponente;
    private double intensidad;
    
    /**
     *  Constructor con parametro
     * 
     * @param recibe un valor de intensidad, correspondiente al oponente.
     */
    public Oponente(double intensidad){
        cartasAleatorio();
        this.intensidad = intensidad;
    }
    
    /**
     * Metodo que reparte cartas al oponente de manera aleatoria.
     * recorre cada carta, asignando uno de los tipos aleatoriamente.
     * 
     */
    public void cartasAleatorio(){
        String[] tiposCarta = {"Agua", "Aire", "Tierra"};
        cartasOponente = new Carta[3]; // inicializar "mazo" con 3 cartas para el oponente.
        for(int i = 0 ; i < cartasOponente.length; i++){
            int aleatorio = (int) (Math.random() * tiposCarta.length);
            
            cartasOponente[i] = new Carta(tiposCarta[aleatorio]); // por cada iteracion recibe un tipo de carta aleatorio.
        }
    }
    
    
    /**
     * Metodo que disminuye la intensidad del oponente.
     * 
     * @return la intensidad disminuida a la mitad.
     */
    public double dismIntensidad(){
        this.intensidad /= 2;
        return intensidad;
    }
    
    /**
     * Metodo que verifica que el oponente actual sigue activo.
     * 
     * @return true, si al menos una de las cartas iteradas tiene vida mayor a 0.
     * @return false, si las cartas tiene vida <= 0.|
     */
    public boolean esDerrotado(){
        for(int i = 0; i < cartasOponente.length; i++){
            
            if(cartasOponente[i].getVida() <= 0){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Imprimir datos
     */
    public String toString(){
        String datosOponente = " ";
        int numCarta = 1; // etiqueta número de carta
        
        for(int i = 0; i < cartasOponente.length; i++){
            datosOponente += "\nCarta N°: " + numCarta + cartasOponente[i].toString();
            numCarta += 1;
        }
        
        return "Datos del oponente:" + datosOponente;
    }
    
    //getters
    public double getIntensidad(){
        return intensidad;
    }
    
    
    public Carta[] getCartasOponente(){
        return cartasOponente;
    }
    
    //setters
    public void setIntensidad(double intensidad){
        this.intensidad = intensidad;
    }
    public void setCartasOponente(Carta[] cartasOponente){
        this.cartasOponente = cartasOponente;
    }
}

