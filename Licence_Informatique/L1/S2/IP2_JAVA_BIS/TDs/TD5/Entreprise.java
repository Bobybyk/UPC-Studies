public class Entreprise {
    private String nom;
    private Cellule premier;

    public Entreprise (String nom) {
        this.nom = nom;
        this.premier = null;
    }

    public void affiche() {
        if (this.premier != null) {
            this.premier.affiche();
        }
        else {
            System.out.println("Aucun employé");
        }   
    }
    
    public boolean appartient (Employé emp) {
        if (this.premier == null) {
            return false;
        }
        else {
            return this.premier.appartient(emp);
        }
    }

    public void ajout (Employé emp) {
        if (!appartient(emp)) {
            Cellule oldP = this.premier;
            this.premier = emp;
            this.premier.setSuivant(oldP);
            Cellule index = oldP; 
        }
    }

    public void demission (String n) {
        if (this.premier.getNom() == n) {
            this.premier = null;    
        }
    }

}