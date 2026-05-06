/**
 * Clase principal que controla el flujo del juego.
 * Maneja el menú, las partidas, los combos y el récord.
 */
public class Juego {

    private Jugador[] equipo;
    private Oponente oponente;
    private double energia;
    private int combosRealizados;
    private String nombreEquipo;

    private Oponente[] historialOponentes;
    private double[] historialIntensidades;

    private static Record mejorRecord = null;

    private Interfaz ui;

    /**
     * Crea una instancia del juego con la interfaz indicada.
     * @param ui la interfaz que se usará para comunicarse con el usuario
     */
    public Juego(Interfaz ui) {
        this.ui = ui;
    }

    /**
     * Muestra el menú principal y espera que el usuario elija una opción.
     * El menú se repite hasta que el usuario decida salir.
     */
    public void menuPrincipal() {
        while (true) {
            ui.mostrar(
                "Bienvenido al Dojo de Supervivencia\n" +
                "1. Ver el historial del mejor juego\n" +
                "2. Iniciar una nueva partida\n" +
                "3. Salir"
            );
            int op = ui.pedirOpcion("Seleccione", 3);
            if (op == 1) mostrarRecord();
            else if (op == 2) iniciarJuego();
            else break;
        }
    }

    /**
     * Muestra el mejor récord registrado en la sesión actual.
     * Si no hay récord todavía, informa al usuario.
     */
    private void mostrarRecord() {
        if (mejorRecord != null) mejorRecord.mostrar(ui);
        else ui.mostrar("No hay récord registrado todavía.");
    }

    /**
     * Actualiza el récord si el resultado actual es mejor que el anterior.
     * @param oponentesDerrotados cantidad de oponentes derrotados en la partida
     */
    private void actualizarRecord(int oponentesDerrotados) {
        int activos = 0;
        for (Jugador j : equipo) if (j.estaActivo()) activos++;
        Record nuevo = new Record(nombreEquipo, oponentesDerrotados, activos, combosRealizados);
        if (nuevo.esMejorQue(mejorRecord)) {
            mejorRecord = nuevo;
            ui.mostrar("¡Nuevo récord registrado para el equipo " + nombreEquipo + "!");
        }
    }

    /**
     * Inicia una nueva partida: pide los nombres del equipo y jugadores,
     * asigna las cartas y enfrenta al equipo contra los tres oponentes.
     */
    private void iniciarJuego() {
        combosRealizados = 0;
        equipo = new Jugador[3];
        historialOponentes = new Oponente[3];
        historialIntensidades = new double[]{0.2, 0.3, 0.4};

        nombreEquipo = ui.pedirTexto("Nombre del equipo");
        for (int i = 0; i < 3; i++) {
            String nombre = ui.pedirTexto("Nombre del jugador " + (i + 1));
            equipo[i] = new Jugador(nombre);
        }

        StringBuilder sb = new StringBuilder("Cartas asignadas:\n");
        for (Jugador j : equipo)
            sb.append("  ").append(j.getNombre()).append(": Aire, Tierra, Agua\n");
        ui.mostrar(sb.toString());

        int derrotados = 0;
        for (int i = 0; i < 3; i++) {
            oponente = new Oponente(historialIntensidades[i]);
            historialOponentes[i] = oponente;
            energia = 1.0;

            if (!pelear(i + 1)) {
                ui.mostrar("El equipo " + nombreEquipo + " fue derrotado en el oponente " + (i + 1) + ".");
                actualizarRecord(derrotados);
                mostrarResumenFinal(derrotados);
                return;
            }
            derrotados++;
            ui.mostrar("¡Oponente " + (i + 1) + " derrotado!");
        }

        ui.mostrar("¡El equipo " + nombreEquipo + " ha ganado el Dojo!");
        actualizarRecord(derrotados);
        mostrarResumenFinal(derrotados);
    }

    /**
     * Lleva a cabo el combate contra un oponente.
     * El equipo realiza combos hasta derrotar al oponente, quedarse sin energía
     * o decidir abandonar.
     * @param numeroOponente el número del oponente actual (1, 2 o 3)
     * @return true si el oponente fue derrotado, false si el equipo perdió o abandonó
     */
    private boolean pelear(int numeroOponente) {
        boolean[] estabaActivo = estadoActivos();

        while (true) {
            if (energia <= 0) {
                ui.mostrar("Se agotó la energía.");
                return false;
            }
            if (!hayJugadoresActivos()) {
                ui.mostrar("Todo el equipo fue derrotado.");
                return false;
            }

            mostrarEstadoPartida(numeroOponente);

            ui.mostrar("1. Realizar un combo\n2. Abandonar el juego");
            int op = ui.pedirOpcion("Seleccione", 2);
            if (op == 2) return false;

            Carta[] cartasCombo = seleccionarCartasCombo();
            if (cartasCombo == null) continue;

            int ataques = ui.pedirEnteroPositivo("Cantidad de ataques para el combo");

            Combo combo = new Combo(cartasCombo, oponente.getCartas(), ataques);
            if (!combo.esValido(equipo)) {
                ui.mostrar("Combo inválido: cada jugador activo debe aportar al menos una carta viva.");
                continue;
            }

            combo.enfrentar();
            combosRealizados++;

            for (int i = 0; i < equipo.length; i++) {
                if (estabaActivo[i] && !equipo[i].estaActivo()) {
                    ui.mostrar(equipo[i].getNombre() + " fue derrotado. La intensidad del oponente se reduce a la mitad.");
                    oponente.reducirIntensidad();
                }
            }
            estabaActivo = estadoActivos();

            energia -= oponente.getIntensidad();
            if (energia < 0) energia = 0;

            if (oponente.estaDerrotado()) return true;
        }
    }

    /**
     * Permite al usuario seleccionar las tres cartas del equipo para un combo.
     * Cada posición corresponde a una carta del oponente.
     * @return el arreglo de cartas seleccionadas
     */
    private Carta[] seleccionarCartasCombo() {
        Carta[] sel = new Carta[3];
        ui.mostrar("Seleccione una carta del equipo para cada posición del oponente.");

        for (int pos = 0; pos < 3; pos++) {
            ui.mostrar("-- Posición " + (pos + 1) + " --");
            while (true) {
                StringBuilder opciones = new StringBuilder("Jugadores activos:\n");
                for (int i = 0; i < equipo.length; i++)
                    if (equipo[i].estaActivo())
                        opciones.append("  ").append(i + 1).append(". ").append(equipo[i].getNombre()).append("\n");
                ui.mostrar(opciones.toString());

                int jIdx = ui.pedirOpcion("Número de jugador", 3) - 1;
                if (!equipo[jIdx].estaActivo()) {
                    ui.mostrar("Ese jugador está derrotado.");
                    continue;
                }

                ui.mostrar("Tipo de carta: 1=Aire  2=Tierra  3=Agua");
                int tIdx = ui.pedirOpcion("Tipo", 3);
                String tipo = (tIdx == 1) ? "Aire" : (tIdx == 2) ? "Tierra" : "Agua";

                Carta c = equipo[jIdx].getCartaPorTipo(tipo);
                if (c == null) {
                    ui.mostrar("Esa carta está muerta o no existe.");
                    continue;
                }
                if (yaSeleccionada(sel, c)) {
                    ui.mostrar("Esa carta ya fue seleccionada.");
                    continue;
                }

                sel[pos] = c;
                break;
            }
        }
        return sel;
    }

    /**
     * Muestra el resumen final de la partida con los atributos completos
     * de todas las cartas del equipo y los oponentes enfrentados.
     * @param derrotados cantidad de oponentes derrotados
     */
    private void mostrarResumenFinal(int derrotados) {
        ui.mostrar(
            "=== FIN DE LA PARTIDA ===\n" +
            "Equipo: " + nombreEquipo + "\n" +
            "Oponentes derrotados: " + derrotados + "/3\n" +
            "Combos realizados: " + combosRealizados
        );

        ui.mostrar("--- Atributos de TU EQUIPO ---");
        for (Jugador j : equipo) j.mostrarEstadoCompleto(ui);

        ui.mostrar("--- Atributos de los OPONENTES ---");
        for (int i = 0; i < historialOponentes.length; i++) {
            if (historialOponentes[i] == null) break;
            ui.mostrar("Oponente " + (i + 1) + " (intensidad original: " + historialIntensidades[i] + "):");
            for (Carta c : historialOponentes[i].getCartas())
                ui.mostrar("    " + c.toString());
        }
    }

    /**
     * Muestra el estado actual de la partida: oponente, energía, combos y vida del equipo.
     * No muestra los atributos completos de las cartas, solo la vida.
     * @param numeroOponente el número del oponente actual
     */
    private void mostrarEstadoPartida(int numeroOponente) {
        ui.mostrar(String.format(
            "Oponente: %d  Intensidad: %.1f%%  Energía disponible: %.1f%%\n" +
            "Combos realizados: %d\nEquipo: %s",
            numeroOponente,
            oponente.getIntensidad() * 100,
            energia * 100,
            combosRealizados,
            nombreEquipo));
        for (Jugador j : equipo) j.mostrarEstadoJuego(ui);
    }

    /**
     * Indica si hay al menos un jugador activo en el equipo.
     * @return true si al menos un jugador sigue activo
     */
    private boolean hayJugadoresActivos() {
        for (Jugador j : equipo) if (j.estaActivo()) return true;
        return false;
    }

    /**
     * Devuelve un arreglo con el estado activo de cada jugador en ese momento.
     * Se usa para detectar quién fue derrotado después de un combo.
     * @return arreglo de booleanos con true si el jugador estaba activo
     */
    private boolean[] estadoActivos() {
        boolean[] estado = new boolean[equipo.length];
        for (int i = 0; i < equipo.length; i++) estado[i] = equipo[i].estaActivo();
        return estado;
    }

    /**
     * Verifica si una carta ya fue seleccionada para el combo actual.
     * @param arr el arreglo de cartas ya seleccionadas
     * @param c la carta que se quiere verificar
     * @return true si la carta ya fue seleccionada
     */
    private boolean yaSeleccionada(Carta[] arr, Carta c) {
        for (Carta x : arr) if (x == c) return true;
        return false;
    }
}
