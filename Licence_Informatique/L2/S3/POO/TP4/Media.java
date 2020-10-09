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
}