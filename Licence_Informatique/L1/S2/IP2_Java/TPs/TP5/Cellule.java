public class Cellule {
	private int valeur;
	private Cellule suivante;

	/*Rappels rapide du cadre :
		1 : On représente une liste vide en déclarant le premier élément comme nul.
		2 : Ce n'est pas absurde car lorsque l'on dit qu'une cellule "contient" une autre cellule,
		elle ne l'a contient pas vraiment mais contient plutôt son adresse
	 */

	public Cellule (int n) {
		this.valeur = n;
	}

	public Cellule (int n, Cellule c) {
		this.valeur = n;
		this.suivante = c;
	}

	public String description () {
		if (this.suivante != null) {
			return (this.valeur + ";" + this.suivante.description());
		} else {
			return (this.valeur + ")");
		}
	}

	public int taille () {
		if (this.suivante == null) {
			return 1;
		} else {
			return (this.suivante.taille() + 1); 
		}
	}

	public int somme () {
		if (this.suivante != null) {
			return (this.valeur + this.suivante.somme());
		} else {
			return (this.valeur);
		}
	}

	public void ajouter_en_i (int i, int v) {
		if (i == 0 && this != null) {
			this.valeur = v;
		} else {
			this.suivante.ajouter_en_i(i-1, v);
		}
	}

	public int getValeur () {
		return this.valeur;
	}
	public Cellule getSuivante () {
		return this.suivante;
	}
	public void setSuivante (Cellule c) {
		this.suivante = c;
	}
}