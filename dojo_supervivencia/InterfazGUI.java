import javax.swing.JOptionPane;

/**
 * Implementación de la interfaz usando ventanas de JOptionPane.
 * Muestra mensajes y recibe entradas del usuario mediante ventanas gráficas.
 */
public class InterfazGUI implements Interfaz {

    /**
     * Muestra un mensaje en una ventana de diálogo.
     * @param mensaje el texto a mostrar
     */
    @Override
    public void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Pide al usuario una opción entre 1 y max mediante una ventana.
     * Repite la pregunta si el valor ingresado no es válido.
     * @param prompt el mensaje que se le muestra al usuario
     * @param max el valor máximo que puede ingresar
     * @return la opción elegida por el usuario
     */
    @Override
    public int pedirOpcion(String prompt, int max) {
        int opcion = 0;
        while (opcion < 1 || opcion > max) {
            String entrada = JOptionPane.showInputDialog(prompt + " [1-" + max + "]");
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcion = 0;
            }
        }
        return opcion;
    }

    /**
     * Pide al usuario que ingrese un texto mediante una ventana.
     * Repite la pregunta si el texto está vacío.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el texto ingresado
     */
    @Override
    public String pedirTexto(String prompt) {
        String entrada = "";
        while (entrada.equals("")) {
            entrada = JOptionPane.showInputDialog(prompt);
            if (entrada == null) {
                entrada = "";
            }
        }
        return entrada;
    }

    /**
     * Pide al usuario un número entero positivo mediante una ventana.
     * Repite la pregunta si el valor ingresado no es válido.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el número ingresado
     */
    @Override
    public int pedirEnteroPositivo(String prompt) {
        int numero = 0;
        while (numero <= 0) {
            String entrada = JOptionPane.showInputDialog(prompt);
            try {
                numero = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                numero = 0;
            }
        }
        return numero;
    }
}
