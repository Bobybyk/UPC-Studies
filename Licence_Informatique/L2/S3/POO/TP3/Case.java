public class Case {
	private boolean etat;
	private Piece piece;

	public Case() {

	}

	public Piece getPiece() {
		return this.piece;
	} 

	public boolean estVide() {
		if (this.etat) {
			return true;
		} return false;
	}

	public void remplirPiece (Piece p) {
		this.piece = p;
	}

	public void enleverPiece(Piece p) {
		this.piece = null;
	} 

	public String toString() {
		if (this.piece != null) {
			return "-";
		} return "*";
	}

}