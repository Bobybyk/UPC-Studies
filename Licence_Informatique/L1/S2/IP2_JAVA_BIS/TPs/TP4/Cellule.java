public class Cellule {
	private Robot rob;
	private Cellule suivant;

	public Cellule (Robot r, Cellule c) {
		this.rob = r;
		this.suivant = c;
	}

	public Cellule (Robot r) {
		this(r, null);
	}

	public void setSuivant (Cellule s) {
		this.suivant = s;
	}

	public Cellule getSuivant() {
		return this.suivant;
	}
	public Robot getRob() {
		return this.rob;
		
	}
}