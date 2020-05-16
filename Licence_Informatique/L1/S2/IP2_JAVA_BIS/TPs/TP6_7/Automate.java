public class Automate {
	private Cellule debut;
	private Cellule fin;

	public Automate() {
		this.debut = null;
		this.fin = null;
	}

	public Automate (String str) {
		for (int i = 0 ; i<str.length() ; i++) {
			if (str.charAt(i) == '#') {
				ajouterFin(true);
			} else {
				ajouterFin(false);
			}
		}
	}

	public void ajouteDebut (boolean d) {
		
		if (this.debut == null) {
			this.debut = new Cellule (d);
			this.fin = this.debut;
		} else {
			Cellule c = new Cellule (d);
			this.debut.setPrecedente(c);
			c.setSuivante(this.debut);
			this.debut = c;
		}
	}

	public void uneEtape() {
		this.prochaineEtape();
		this.miseAJour();
	}

	public void nEtapes (int n) {
		this.affiche();
		for(int i=0 ; i<n ; i++){
			this.uneEtape();
			this.affiche();
		}
	}

	public void ajouterFin (boolean f) {

		if (this.fin == null) {
			this.fin = new Cellule (f);
			this.debut = this.fin;
		} else {
			Cellule c = new Cellule (f);
			this.fin.setSuivante(c);
			c.setPrecedente(this.fin);
			this.fin = c;
		}
	}

	public void initialisation() {
		ajouteDebut(false);
		ajouteDebut(false);
		ajouteDebut(false);
		ajouteDebut(false);
		ajouteDebut(true);
		ajouteDebut(false);
		ajouteDebut(false);
		ajouteDebut(false);
		ajouteDebut(true);
		ajouteDebut(true);
		ajouteDebut(true);
	}

	public void prochaineEtape() {
		Cellule index = this.debut;

		if (this.debut != null) {
			while(index != null) {
				index.prochaineEtape();
				index = index.getSuivante();
			}
		}
	}

	public void miseAJour() {
		Cellule index = this.debut;
		while(index != null){
			index.setNoire(index.getProchain());
			index = index.getSuivante();
		}
	}

	public void affiche() {
		Cellule index = this.debut;
		if (this.debut != null) {
			while (index.getSuivante() != null) {
				index.affiche();
				index = index.getSuivante();

			}
			index.affiche();
			System.out.println("");
		} else {
			System.out.println("La liste est vide.");
		}
	}
}