public class Cellule {
    private Cellule suivant;
    private Cellule precedent;
    public Enfant enfant;

    public Cellule (Enfant e) {
        this.enfant = e;
        this.suivant = null;
        this.precedent = null;
    }

    
}