/**
 * Punto de entrada del programa.
 * Decide si usar interfaz de consola o gráfica según el parámetro recibido.
 *
 * Uso:
 *   java Main            - modo consola (por defecto)
 *   java Main -consola   - modo consola
 *   java Main -gui       - modo gráfico (JOptionPane)
 */
public class Main {

    /**
     * Método principal que inicia el juego.
     * @param args parámetros de línea de comandos (-gui o -consola)
     */
    public static void main(String[] args) {
        Interfaz ui;

        if (args.length > 0 && args[0].equalsIgnoreCase("-gui")) {
            ui = new InterfazGUI();
        } else {
            ui = new InterfazConsola();
        }

        new Juego(ui).menuPrincipal();
    }
}
