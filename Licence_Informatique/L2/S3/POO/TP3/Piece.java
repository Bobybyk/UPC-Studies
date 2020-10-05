public class Piece {
	private boolean pCouleur;
	private String nom;

	public Piece (boolean pC, String n) {
		this.pCouleur = pC;
		this.nom = n;
	} 

	public String toString() {
		if (this.pCouleur) {
			return this.nom;
		}
		else {
			String s = "";
			s += this.nom.charAt(0);
			s = s.toUpperCase();
			for (int i = 1 ; i<this.nom.length() ; i++) {
				s+=this.nom.charAt(i);
			}
			return s;
		}
	}



}