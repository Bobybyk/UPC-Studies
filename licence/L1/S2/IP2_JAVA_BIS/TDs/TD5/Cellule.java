public class Cellule {
    private Employé emp;
    private Cellule suivant;

    public Cellule (Employé emp) {
        this(emp, null);
    }
    public Cellule (Employé emp, Cellule suiv) {
        this.emp = emp;
        this.suivant = suiv;
    }

    public void affiche() {
        Cellule index = this;
        if (index != this.suivant()) {
            this.emp.affiche();
            index = this.suivant;
            index.affiche();
        }
        else {
            this.emp.affiche();
        }
    }

    public boolean appartient (Employé emp) {
        if (this.emp.getNom() == emp) {
            return true;
        }
        else {
            return this.suivant.appartient(emp);
        }
    }

    public Cellule getSuivant() {
        return this.suivant;
    }
    
    public void setSuivant(Cellule c) {
        this.suivant = c;
    }
}