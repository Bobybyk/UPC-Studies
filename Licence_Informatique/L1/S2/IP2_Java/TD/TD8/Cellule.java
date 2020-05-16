public class Cellule {
	private Enfant contenu;
	private Cellule suiv;
	private Cellule prec;

	public Cellule (Enfant e) {
		this.contenu = e;
		this.suiv = this; //Assigne la cellule courante comme suivant d'elle même
		this.prec = this; //Assigne la cellule courante comme precedente d'elle même
	}

	public void ajouter (String nom, int nb) {
		Cellule oldLast = this.prec;
		this.prec = new Cellule (new Enfant);
		this.prec.suiv = this;
		this.prec.prec = oldLast;
	}

	public void supprimer (String nom) {
		Cellule tmp = this;
		do {
			if (tmp.contenu.getNom().equals(nom)) {
				tmp.prec.suiv = tmp.suiv;
				tmp.suiv.prec = tmp.prec;
			}
			tmp = tmp.suiv;
		}
		while (tmp.suiv != this);
	}

	public void afficher() {
		System.out.println(this.contenu.getNom() + "," + this.contenu.getNb());
	}
}