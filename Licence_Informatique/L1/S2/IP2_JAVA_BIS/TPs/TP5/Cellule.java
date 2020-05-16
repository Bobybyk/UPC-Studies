public class Cellule {
	private int valeur;
	private Cellule suivante;

	public Cellule (int x) {
		this.valeur = x;
	}
	public Cellule (int x, Cellule c) {
		this.valeur = x;
		this.suivante = c;
	}

	public int taille() {
		if (this.suivante == null) {
			return 1;
		} else {
			return this.suivante.taille() + 1;
		}
	}

	public int somme() {
		if (this.suivante != null ) {
			return this.suivante.somme() + this.valeur;
		} else {
			return this.valeur;
		}
	}

	public void ajouter_en_i (int i, int v) {
		if (i == 0 && this != null) {
			this.valeur = this.valeur + v;
		} else {
			this.suivante.ajouter_en_i(i-1, v);
		}
	}

 	public void supprimer_en_i (int i) {
			if (i == 1) {
				this.suivante = this.suivante.suivante;
			} else {
				this.suivante.supprimer_en_i(i-1);
			}
	}

	public int supprimer_k_premieres_occ (int k, int v) {
		int x = 0;
		if (k != 0) {
			if (this.suivante.valeur == v) {
				supprimer_en_i(k);
				x++;
			} else {
				this.suivante.supprimer_k_premieres_occ(k-1, v);
			}
		}
		return x;
	}

	public String description () {
		if (this.suivante != null) {
			return (this.valeur + ";" + this.suivante.description());
		} else {
			return (this.valeur + ")");
		}
	}

	public int getVal() {
		return this.valeur;
	}
	public Cellule getSuivante() {
		return this.suivante;
	}

	public void setSuivante (Cellule c) {
		this.suivante = c;
	}

}
	
	