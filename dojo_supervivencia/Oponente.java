// clase del oponente
public class Oponente {

    private Carta[] cartasOponente;
    private double intensidad;

    public Oponente(double intensidad) {
        this.intensidad = intensidad;
        cartasAleatorio();
    }
    //genera cartas aleatorias para los jugadores
    public void cartasAleatorio() {
        String[] tipos = {"Agua", "Aire", "Tierra"};
        cartasOponente = new Carta[3];

        for (int i = 0; i < 3; i++) {
            int r = (int) (Math.random() * 3);
            cartasOponente[i] = new Carta(tipos[r]);
        }
    }
    // verifica si el oponente sigue vivo
    public boolean estaDerrotado() {
        for (Carta c : cartasOponente) {
            if (!c.estaMuerta()) return false;
        }
        return true;
    }

    public double getIntensidad() { return intensidad; }

    public Carta[] getCartas() { return cartasOponente; }
}