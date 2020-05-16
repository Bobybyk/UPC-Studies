public class Couloir {
	private ListeSalles listeDeSalles;
	private Couloir suivant;	
	private Couloir precedent;

	public int profondeur() {
		Couloir c = this.precedent;
		int n = 0;
		while (c != null) {
			c = c.precedent;
			n++;
		}
		return n;
	}

}