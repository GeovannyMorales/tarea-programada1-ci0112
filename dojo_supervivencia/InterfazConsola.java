import java.util.Scanner;

/**
 * Implementación de la interfaz usando la consola.
 * Usa Scanner para leer la entrada del usuario y System.out para mostrar mensajes.
 */
public class InterfazConsola implements Interfaz {

    private Scanner sc = new Scanner(System.in);

    /**
     * Muestra un mensaje en la consola.
     * @param mensaje el texto a mostrar
     */
    @Override
    public void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Pide al usuario una opción entre 1 y max por consola.
     * Repite la pregunta si el valor ingresado no es válido.
     * @param prompt el mensaje que se le muestra al usuario
     * @param max el valor máximo que puede ingresar
     * @return la opción elegida por el usuario
     */
    @Override
    public int pedirOpcion(String prompt, int max) {
        while (true) {
            System.out.print(prompt + " [1-" + max + "]: ");
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= 1 && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Opción inválida, intente de nuevo.");
        }
    }

    /**
     * Pide al usuario que ingrese un texto por consola.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el texto ingresado
     */
    @Override
    public String pedirTexto(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    /**
     * Pide al usuario un número entero positivo por consola.
     * Repite la pregunta si el valor ingresado no es válido.
     * @param prompt el mensaje que se le muestra al usuario
     * @return el número ingresado
     */
    @Override
    public int pedirEnteroPositivo(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v > 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Ingrese un número mayor a 0.");
        }
    }
}
