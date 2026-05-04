import java.util.Scanner;
/**
 * Clase Main(pruebas de momento)
 * 
 * @author Bryan Morales, Maria Vargas, José Rojas 
 * @version 1.0
 */
public class Main
{
   public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       //Carta carta1 = new Carta();
       //Carta carta2 = new Carta();
       
       
       System.out.println("Cual es tu nombre?");
       String nombre1 = sc.nextLine();
       Jugador jugador1 = new Jugador(nombre1);
       
       
       
       
       
       System.out.println(jugador1.toString());
       
       //System.out.println(carta1.toString());
       sc.close();
       
       //asigna una tipo diferente a la segunda carta
      
    
       //System.out.println(carta1.getTipo());
       //System.out.println(carta2.getTipo());
       
       //agregar clase oponente, equipo
   }
}