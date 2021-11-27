public class Piece {
	private boolean couleur;
	private String nom;

	public Piece (boolean couleur, String n) {
		this.couleur = couleur;
		this.nom = n;
	} 

	public String toString() {
		if (this.couleur) {
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

	public boolean estValide (Deplacement d, Plateau p) {
		return (p.horsLimite(d.getX1(), d.getY1()) && p.getCase()[d.getX1()][d.getY0()].estVide() || p.getCase()[d.getX1()][d.getY0()].getPiece().couleur != this.couleur);
	}



}