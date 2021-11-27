public class Media {
	private String titre;
	public static int num = 0;
	private final int numero;

	public Media (String t) {
		this.titre = t;
		num++;
		this.numero = num;
	}

	public int getNumero() {
		return this.numero;
	}

	public String toString() {
		return this.titre + ", l'identidiant est : " + this.numero;
	}

	public boolean plusPetit (Media doc) {
		if (this.numero < doc.numero) {
			return true;
		} return false;

	}

	public int ordreMedia() {
		if (this instanceof Dictionnaire) { return 1; }
		if (this instanceof Livre) { return 2; }
		else { return 3; }
	}
}