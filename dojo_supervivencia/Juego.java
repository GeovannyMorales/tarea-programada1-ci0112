import java.util.Scanner;
import javax.swing.JOptionPane;

public class Juego {

    private Jugador[] equipo;
    private Oponente oponente;
    private double energia;
    private int combosRealizados;

    private String nombreEquipo;

    private static Record mejorRecord = null;

    private Scanner sc;
    private boolean modoGUI;

    public Juego(boolean modoGUI) {
        this.modoGUI = modoGUI;
        sc = new Scanner(System.in);
        equipo = new Jugador[3];
    }

    public static void main(String[] args) {

        boolean modoGUI = false;

        if (args.length > 0 && args[0].equals("-gui")) {
            modoGUI = true;
        }

        Juego juego = new Juego(modoGUI);
        juego.menu();
    }

    private String leerTexto(String mensaje) {
        if (modoGUI) {
            return JOptionPane.showInputDialog(mensaje);
        } else {
            System.out.print(mensaje);
            return sc.nextLine();
        }
    }

    private int leerEntero(String mensaje) {
        if (modoGUI) {
            return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        } else {
            System.out.print(mensaje);
            int num = sc.nextInt();
            sc.nextLine();
            return num;
        }
    }

    private void mostrar(String mensaje) {
        if (modoGUI) {
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            System.out.println(mensaje);
        }
    }

    public void menu() {

        while (true) {

            mostrar("\n DOJO DE SUPERVIVENCIA  \n1. Ver record\n2. Iniciar el juego\n3. Salir");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    mostrarRecord();
                    break;
                case 2:
                    iniciar();
                    break;
                case 3:
                    mostrar("Has salido");
                    System.exit(0);
                    break;
            }
        }
    }

    private void mostrarRecord() {
        if (mejorRecord != null) {
            mejorRecord.mostrar();
        } else {
            mostrar("No existe record");
        }
    }

    public void iniciar() {

        combosRealizados = 0;

        nombreEquipo = leerTexto("¿Como se llama tu equipo? ");

        for (int i = 0; i < 3; i++) {
            String nombre = leerTexto("Jugador " + (i + 1) + ": ");
            equipo[i] = new Jugador(nombre);
        }

        int oponentesDerrotados = 0;

        for (int i = 1; i <= 3; i++) {

            mostrar("\n--- Oponente " + i + " ---");

            double intensidad = obtenerIntensidad(i);
            oponente = new Oponente(intensidad);

            energia = 1.0;

            boolean ganado = pelear();

            if (!ganado) {
                mostrar("Has perdido contra el " + i + " oponente");
                actualizarRecord(oponentesDerrotados);
                return;
            }

            oponentesDerrotados++;
        }

        mostrar("¡Completaste el juego!");
        actualizarRecord(oponentesDerrotados);
    }

    private double obtenerIntensidad(int n) {
        if (n == 1) return 0.2;
        if (n == 2) return 0.3;
        return 0.4;
    }

    private boolean pelear() {

        while (energia > 0) {

            mostrarEstado();

            int opcion = leerEntero("1. Hacer combo\n2. Abandonar");

            if (opcion == 2) return false;

            Carta[] cartasEquipo = seleccionarCartas();
            Carta[] cartasOponente = oponente.getCartas();

            int ataques = leerEntero("Cantidad de ataques: ");

            Combo combo = new Combo(cartasEquipo, cartasOponente, ataques);

            if (!combo.esValido(equipo)) {
                mostrar("Combo invalido");
                continue;
            }

            combo.ejecutar();
            combosRealizados++;

            energia -= oponente.getIntensidad();

            if (oponente.estaDerrotado()) {
                mostrar("Oponente derrotado");
                return true;
            }

            if (!hayJugadoresActivos()) {
                return false;
            }
        }

        return false;
    }

    private Carta[] seleccionarCartas() {

        Carta[] seleccion = new Carta[3];

        for (int i = 0; i < 3; i++) {

            while (true) {

                int jugadorIndex = leerEntero("Seleccion carta " + (i + 1) + "\nJugador (1-3): ") - 1;

                if (jugadorIndex < 0 || jugadorIndex > 2) {
                    mostrar("Jugador invalido");
                    continue;
                }

                Jugador jugador = equipo[jugadorIndex];

                if (!jugador.estaActivo()) {
                    mostrar("Ese jugador esta derrotado");
                    continue;
                }

                int opcion = leerEntero("Tipo: 1=Aire  2=Tierra  3=Agua");

                String tipo = "";
                if (opcion == 1) tipo = "Aire";
                else if (opcion == 2) tipo = "Tierra";
                else if (opcion == 3) tipo = "Agua";
                else {
                    mostrar("Tipo invalido");
                    continue;
                }

                Carta carta = jugador.getCartaPorTipo(tipo);

                if (carta == null || carta.estaMuerta()) {
                    mostrar("Carta no disponible");
                    continue;
                }

                if (yaSeleccionada(seleccion, carta)) {
                    mostrar("Carta repetida");
                    continue;
                }

                seleccion[i] = carta;
                break;
            }
        }

        return seleccion;
    }

    private boolean yaSeleccionada(Carta[] seleccion, Carta carta) {
        for (Carta c : seleccion) {
            if (c == carta) return true;
        }
        return false;
    }

    private boolean hayJugadoresActivos() {
        for (Jugador j : equipo) {
            if (j.estaActivo()) return true;
        }
        return false;
    }

    private void mostrarEstado() {

        String estado = "\nESTADO\n";
        estado += "Energia: " + String.format("%.2f", energia) + "\n";
        estado += "Intensidad: " + String.format("%.2f", oponente.getIntensidad()) + "\n";
        estado += "Combos hechos: " + combosRealizados + "\n\n";

        for (Jugador j : equipo) {
            estado += j.toString() + "\n";
        }

        estado += "\nOPONENTE\n";

        Carta[] cartasOp = oponente.getCartas();

        for (int i = 0; i < cartasOp.length; i++) {
            estado += "Carta " + (i + 1) +
                      " Vida: " + String.format("%.2f", cartasOp[i].getVida()) + "\n";
        }

        mostrar(estado);
    }

    private void actualizarRecord(int oponentesDerrotados) {

        int jugadoresActivos = 0;

        for (Jugador j : equipo) {
            if (j.estaActivo()) {
                jugadoresActivos++;
            }
        }

        Record nuevo = new Record(
            nombreEquipo,
            oponentesDerrotados,
            jugadoresActivos,
            combosRealizados
        );

        if (nuevo.esMejorQue(mejorRecord)) {
            mejorRecord = nuevo;
            mostrar("¡NUEVO RECORD!");
        }
    }
}