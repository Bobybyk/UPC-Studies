public class Cellule {
    private Cellule suivant;
    private Cellule precedent;
    public Enfant enfant;

    public Cellule (Enfant e) {
        this.enfant = e;
        this.suivant = null;
        this.precedent = null;
    }

    public void setSuivant (Cellule c) {
        this.suivant = c;
    }
    public void setPrecedent (Cellule c) {
        this.precedent = c;
    }

    public Cellule getSuivant() {
        return this.suivant;
    }
    public Cellule getPrecedent() {
        return this.precedent;
    }
}