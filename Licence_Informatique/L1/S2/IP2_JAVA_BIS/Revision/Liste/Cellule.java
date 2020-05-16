public class Cellule {
	private Cellule suivant;
	private Cellule precedent;
	private int valeur;

	public Cellule (int val) {
		this.suivant = null;
		this.precedent = null;
		this.valeur = val;
	}

	public void affiche() {

		if (this.suivant == null) {
			System.out.println(this.valeur);
		} else {
			System.out.println(this.valeur);
			this.suivant.affiche();
		}
	}

	public void additionne() {
		if (this.suivant != null) {
			this.suivant.valeur = this.suivant.valeur + this.valeur;
			this.valeur = 0;
			this.suivant.additionne(); 
		}
	}

	public Cellule getSuivant() {
		return this.suivant;
	}
	public Cellule getPrecedent() {
		return this.precedent;
	}
	public int getValeur() {
		return this.valeur;
	}

	public void setSuivant (Cellule suivant) {
		this.suivant = suivant;
	}
	public void setPrecedent (Cellule precedent) {
		this.precedent = precedent;
	}
	public void setValeur(int val) {
		this.valeur = val;
	}
}