
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
   public static void main(String[] args){
       
       Carta carta1 = new Carta();
       Carta carta2 = new Carta();
       
       //asigna una tipo diferente a la segunda carta
       while(carta2.getTipo().equals(carta1.getTipo())){
           carta2.setTipo(carta2.asignarTipo());
       }
    
       System.out.println(carta1.getTipo());
       System.out.println(carta2.getTipo());
   }
}