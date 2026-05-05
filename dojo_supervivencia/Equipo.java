
/**
 * Write a description of class Equipo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipo
{
    private Jugador[] equipo;
    private String nombre;
    private double energia;
    
    
    public Equipo(String nombre, Jugador[] equipo){
        this.equipo = equipo;
        this.nombre = nombre;
        this.energia = 1.0;
    }
    

      public String toString(){
        String datosEquipo = " ";
        int numJugador = 1; // etiqueta número de carta
        
        for(int i = 0; i < equipo.length; i++){
            datosEquipo += "\nJugador N°:" + numJugador + equipo[i].toString();
            numJugador += 1;
        }
        
        return " nombre: " + nombre + datosEquipo;
    }
    
    /**
     * Metodo para disminuir la energía del equipo.
     */
    public void dismEnergia(double intensidad){
        energia -= intensidad;
    }
    
    /*
     * El equipo tendria 3 jugadores, los cuales tienen 3 cartas. pero habria que inicializar a los 3 jugadores en el equipo
     */
    
    public double getEnergia() {
        return energia;
    }
    
    public void setEnergia(double energia){
        this.energia = energia;
    }
}