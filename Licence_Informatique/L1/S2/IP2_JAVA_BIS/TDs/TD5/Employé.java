public class Employé {
    private final String nom;
    private int salaire;

    public Employé (String n, int s) {
        this.nom = n;
        this.salaire = s;
    }

    public void affiche() {
        System.out.println(this.nom);
    }

    public String getNom() {
        return this.nom;
    }
    public int getSalaire() {
        return this.salaire;
    }

    public void setSalaire(int s) {
        this.salaire = s;
    }
}