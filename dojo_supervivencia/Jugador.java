// clase que representa al jugador
public class Jugador {

    private String nombre;
    private Carta[] cartasJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        recibirCartas();
    }

    // crea las cartas del jugador
    public void recibirCartas() {
        String[] tipos = {"Aire", "Tierra", "Agua"};
        cartasJugador = new Carta[3];

        for (int i = 0; i < 3; i++) {
            cartasJugador[i] = new Carta(tipos[i]);
            cartasJugador[i].setJugador(this);
        }
    }

    // verifica si sigue activo
    public boolean estaActivo() {
        for (Carta c : cartasJugador) {
            if (!c.estaMuerta()) return true;
        }
        return false;
    }

    // obtiene carta por tipo
    public Carta getCartaPorTipo(String tipo) {
        for (Carta c : cartasJugador) {
            if (c.getTipo().equals(tipo)) return c;
        }
        return null;
    }

    // muestra estado del jugador
    public void mostrarEstado() {
        System.out.println("Jugador: " + nombre + " | Estado: " + (estaActivo() ? "Activo" : "Derrotado"));

        for (Carta c : cartasJugador) {
            System.out.println(" - " + c.getTipo() + " | Vida: " + String.format("%.2f", c.getVida()));
        }
    }

    public Carta[] getCartaJugadores() { return cartasJugador; }
    public String getNombre() { return nombre; }
}