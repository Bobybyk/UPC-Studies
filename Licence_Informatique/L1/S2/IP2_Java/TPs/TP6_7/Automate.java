public class Automate {
	private Cellule premier;
	private Cellule fin;

	public Automate () {
		this.premier = null;
		this.fin = null;
	}

	public void ajouter_debut (boolean d) {
		
		if (this.premier == null) {
			this.premier = new Cellule (d);
			this.fin = this.premier;
		} else {
			Cellule c = new Cellule (d);
			this.premier.setPrecedente(c);
			c.setSuivante(this.premier);
			this.premier = c;
		}

		
	}

	public void ajouter_fin (boolean f) {

		if (this.fin == null) {
			this.fin = new Cellule (f);
			this.premier = this.fin;
		} else {
			Cellule c = new Cellule (f);
			this.fin.setSuivante(c);
			c.setPrecedente(this.fin);
			this.fin = c;
		}
	}

	public void initialistation () {
		
		ajouter_debut(false);
		ajouter_debut(false);
		ajouter_debut(false);
		ajouter_debut(false);
		ajouter_debut(true);
		ajouter_debut(false);
		ajouter_debut(false);
		ajouter_debut(false);
		ajouter_debut(true);
		ajouter_debut(true);
		ajouter_debut(true);
	}

	public void prochaineEtape() {
		
		Cellule index = this.premier;

		if (this.premier != null) {
			while(index != null) {
				index.prochaineEtape();
				index = index.getSuivante();
			}
		}
	}

	public void miseAJour() {
		
		Cellule index = this.premier;
		while(index != null){
			index.setNoire(index.getProchainEtat());
			index = index.getSuivante();
		}

	}

	public void uneEtape() {
		this.prochaineEtape();
		this.miseAJour();		
	}

	public void nEtapes(int n) {
		this.afficher();
		for(int i=0 ; i<n ; i++){
			this.uneEtape();
			this.afficher();
		}
	}

	public void afficher () {

		if (this.premier == null) {
			System.out.println("La liste est vide");
		} else {
			Cellule index = this.premier;

			while(index != null) {
				index.afficher();

				index = index.getSuivante();
			}
			System.out.println();
		}
	}
}