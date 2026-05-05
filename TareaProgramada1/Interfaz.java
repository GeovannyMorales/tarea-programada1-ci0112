/**
 * Interfaz que define los métodos de comunicación con el usuario.
 * Permite que el juego funcione tanto en consola como con ventanas (JOptionPane)
 * sin cambiar el código del juego.
 */
public interface Interfaz {

    /**
     * Muestra un mensaje al usuario.
     * @param mensaje el texto a mostrar
     */
    void mostrar(String mensaje);

    /**
     * Pide al usuario que elija una opción numérica entre 1 y max.
     * @param prompt el mensaje que se le muestra al usuario
     * @param max el valor máximo que puede ingresar
     * @return la opción elegida por el usuario
     */
    int pedirOpcion(String prompt, int max);

    /**
     * Pide al usuario que ingrese un texto.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el texto ingresado
     */
    String pedirTexto(String prompt);

    /**
     * Pide al usuario que ingrese un número entero positivo.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el número ingresado
     */
    int pedirEnteroPositivo(String prompt);
}
