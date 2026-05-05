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
       
       
       System.out.println("Nombre jugador 1?");
       String nombre1 = sc.nextLine();
       Jugador jugador1 = new Jugador(nombre1);
       
       System.out.println("Nombre jugador 2?");
       String nombre2 = sc.nextLine();
       Jugador jugador2 = new Jugador(nombre2);
       
       System.out.println("Nombre jugador 3?");
       String nombre3 = sc.nextLine();
       Jugador jugador3 = new Jugador(nombre3);
       
       System.out.println("Nombre equipo?");
       String equipo = sc.nextLine();
       
       Jugador[] jugadores = {jugador1, jugador2, jugador3};
       
       //System.out.println(jugador1.toString());
       Equipo equipo1 = new Equipo(equipo,jugadores );
       
       System.out.println(equipo1.toString());
       
       //System.out.println(carta1.toString());
       sc.close();
       
       //asigna una tipo diferente a la segunda carta
      
    
       //System.out.println(carta1.getTipo());
       //System.out.println(carta2.getTipo());
       
       //agregar clase oponente, equipo
   }
}