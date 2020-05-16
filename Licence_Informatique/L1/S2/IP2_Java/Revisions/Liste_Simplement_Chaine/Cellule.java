public class Cellule {
	private int valeur;
	private Cellule suivante;

	public Cellule (int n) {
		this.valeur = n;
		this.suivante = null;
	}

	public Cellule (int n, Cellule c) {
		this.valeur = n;
		this.suivante = c;
	}


	public void afficher() {
		System.out.print(this.valeur);
	}


	public Cellule getSuivante() {
		return this.suivante;
	}
	public int getVal() {
		return this.valeur;
	}

	public void setSuivante (Cellule c) {
		this.suivante = c;
	}
	public void setVal (int x) {
		this.valeur = x;
	}
}