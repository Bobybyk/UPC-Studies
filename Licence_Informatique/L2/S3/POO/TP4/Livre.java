public class Livre extends Media {
	private String auteur;
	private int nbrPages;

	public Livre (String a, int p, String t) {
		super(t);
		this.auteur = a;
		this.nbrPages = p;
	}

	public String toString() {
		return "Livre écrit par " + this.auteur + " et contient " + this.nbrPages + ". Il appartient à " + super.toString();
	}
}