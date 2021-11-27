public class FileToboggan {
	private Cellule courant;
	private int nbMax;

	public FileToboggan (int n) {
		this.nbMax = n;
		this.courant = null;
	}

	public void ajouter (String nom, int nb) {
		if (this.courant == null) {
			this.courant = new Enfant (nom, nb);
		} else {
			this.courant.ajouter(nom, nb);
		}
	}

	public void supprimer (String nom) {
		if (this.courant != null) {
			if (this.courant.getSuiv() == this) {
				if (this.courant.getContenu().getNom().equals(nom)) {
					this.courant = null;
				}
			} else {
				this.courant.supprimer(nom);
			} 
		}
	}

	public void afficher() {
		if (this.courant == null) {
			return;
		} else {
			Cellule tmp = this.courant;
			while (tmp != this.courant) {
				tmp.afficher();
				tmp = tmp.getSuiv();
			}
		}
		tmp.afficher();
	}
}