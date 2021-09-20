public class Complexe {
    private double reel, imaginaire;

    public Complexe(double reel, double imaginaire) {
        this.reel = reel;
        this.imaginaire = imaginaire;
    } 

    public String toString() {
        // a+ib
        return reel + "+" + imaginaire + "i";
    }

    public void somme(Complexe c) {
        reel += c.reel;
        imaginaire += c.imaginaire;
    }

    public void soustraction(Complexe c) {
        reel -= c.reel;
        imaginaire -= c.imaginaire;
    }

    public void multiplication(Complexe c) {
        reel *= c.reel;
        imaginaire *= c.imaginaire;
    }

    public void division(Complexe c) {
        reel /= c.reel;
        imaginaire /= c.imaginaire;
    }

    public boolean equals(Object other) {
        return ((Complexe) other).getImaginaire() == imaginaire && ((Complexe) other).getReel() == reel;
    }

    public double getReel() {
        return reel;
    }
    public double getImaginaire() {
        return imaginaire;
    }
}