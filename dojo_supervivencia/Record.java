public class Record {

    private String nombreEquipo;
    private int oponentesDerrotados;
    private int jugadoresActivos;
    private int combos;

    public Record(String nombreEquipo, int oponentesDerrotados, int jugadoresActivos, int combos) {
        this.nombreEquipo = nombreEquipo;
        this.oponentesDerrotados = oponentesDerrotados;
        this.jugadoresActivos = jugadoresActivos;
        this.combos = combos;
    }

    public boolean esMejorQue(Record otro) {

        if (otro == null) return true;

        if (this.oponentesDerrotados > otro.oponentesDerrotados) return true;
        if (this.oponentesDerrotados < otro.oponentesDerrotados) return false;

        if (this.jugadoresActivos > otro.jugadoresActivos) return true;
        if (this.jugadoresActivos < otro.jugadoresActivos) return false;

        return this.combos < otro.combos;
    }

    public void mostrar() {
        System.out.println("MEJOR EQUIPO: " + nombreEquipo);
        System.out.println("Oponentes derrotados: " + oponentesDerrotados);
        System.out.println("Jugadores activos: " + jugadoresActivos);
        System.out.println("Combos usados: " + combos);
    }
}