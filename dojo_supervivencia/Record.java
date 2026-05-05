/**
 * Representa el récord de un equipo al finalizar su partida.
 * Guarda el nombre del equipo, oponentes derrotados, jugadores activos y combos usados.
 */
public class Record {

    private String nombreEquipo;
    private int oponentesDerrotados;
    private int jugadoresActivos;
    private int combos;

    /**
     * Crea un nuevo récord con los datos del equipo al terminar la partida.
     * @param nombreEquipo el nombre del equipo
     * @param oponentesDerrotados cantidad de oponentes que derrotó el equipo
     * @param jugadoresActivos cantidad de jugadores que quedaron activos
     * @param combos cantidad total de combos realizados
     */
    public Record(String nombreEquipo, int oponentesDerrotados, int jugadoresActivos, int combos) {
        this.nombreEquipo = nombreEquipo;
        this.oponentesDerrotados = oponentesDerrotados;
        this.jugadoresActivos = jugadoresActivos;
        this.combos = combos;
    }

    /**
     * Compara si este récord es mejor que otro.
     * Criterios en orden: más oponentes derrotados, más jugadores activos, menos combos usados.
     * @param otro el récord con el que se compara
     * @return true si este récord es mejor que el otro
     */
    public boolean esMejorQue(Record otro) {
        if (otro == null) return true;

        if (this.oponentesDerrotados > otro.oponentesDerrotados) return true;
        if (this.oponentesDerrotados < otro.oponentesDerrotados) return false;

        if (this.jugadoresActivos > otro.jugadoresActivos) return true;
        if (this.jugadoresActivos < otro.jugadoresActivos) return false;

        return this.combos < otro.combos;
    }

    /**
     * Muestra el récord usando la interfaz del juego.
     * @param ui la interfaz que se usa para mostrar el mensaje
     */
    public void mostrar(Interfaz ui) {
        ui.mostrar("=== MEJOR MARCADOR ===");
        ui.mostrar("Equipo:               " + nombreEquipo);
        ui.mostrar("Oponentes derrotados: " + oponentesDerrotados);
        ui.mostrar("Jugadores activos:    " + jugadoresActivos);
        ui.mostrar("Combos usados:        " + combos);
    }
}
