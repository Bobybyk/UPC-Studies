public class Cellule {
	private Robot rob;
	private Cellule suivant;

	public Cellule () {
		this.rob = null;
		this.suivant = null;
	}
	public Cellule (Robot r, Cellule c) {
		this.rob = r;
		this.suivant = c;
	}
	public Cellule (Robot r) {
		this(r, null);
	}

	public Cellule getSuivant() {
		return this.suivant;
	}

	public Robot getRob() {
		return this.rob;
	}

	public void setSuivant(Cellule s) {
		this.suivant = s;
	}
}

