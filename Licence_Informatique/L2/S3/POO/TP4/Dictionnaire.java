public class Dictionnaire extends Media {
	private String langue;
	private int nbrTomes;

	public Dictionnaire (String l, int t, String titre) {
		super(titre);
		this.langue = l;
		this.nbrTomes = t;
	}

	public String toString() {
		return "Dictionnaire en " + this.langue + " en " + this.nbrTomes + " tomes. Il appartient à " + super.toString();
	}
}