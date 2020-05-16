public class Objets {
	private final String nom;
	private int note;

	public Objets (String nom, int not) {
		this.nom = nom;
		this.note = not;
	}

	public boolean meilleur (int n) {
		if (this.note > n) {
			return true;
		}
		return false;
	}


	public String getNom() {
		return this.nom;
	}
	public int getNote() {
		return this.note;
	}
 
}