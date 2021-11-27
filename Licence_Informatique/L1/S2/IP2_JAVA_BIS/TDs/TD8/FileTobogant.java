public class FileTobogant {
    private Cellule courant;
    private int nbMax;

    public FileTobogant (int n) {
        this.nbMax = n;
        this.courant = null;
    }

    public void ajouter (String nom, int nb) {
        Cellule c = new Cellule(new Enfant(nom, nb));
        if (this.courant == null) {
            this.courant = c;
            c.setPrecedent(c);
            c.setSuivant(c);
        }
        else {
            c.setSuivant(this.courant);
            c.setPrecedent(this.courant.getPrecedent());
            this.courant.getPrecedent().setSuivant(c);
            this.courant.setPrecedent(c);
        }
    }

    public void supprimer (String nom) {
        while (this.courant.getSuivant() != this.courant) {
            if (this.courant.getSuivant().getNom() == nom) {
                this.courant.setSuivant(this.courant.getSuivant().getSuivant());
                this.courant.getSuivant().setPrecedent(this.courant);
            }
        }
    }

    public void affiche() {
        index = this.courant;
        while (index.getSuivant() != this.courant) {
            System.out.println(index.getNom());
            index = index.getSuivant();
        }
    }
}