public class Compteur {
    private int vitesse;
    private double distance;
    public static String nom = "lambda";

    public Compteur(int v, int d) {
        this.vitesse = v;
        this.distance = d;
    } 

    public void setVitesse (int v) {
        this.vitesse = v;
    }
    public void setDistance (int d) {
        this.distance = d;
    }
}