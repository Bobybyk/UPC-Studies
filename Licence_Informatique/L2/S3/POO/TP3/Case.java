public class Case {
	private boolean etat;
	private Piece piece;

	public Case(boolean etat) {
		this.etat = etat;
		this.piece = null;
	}

	public Piece getPiece() {
		return this.piece;
	} 

	public boolean estVide() {
		if (this.piece != null) {
			return true;
		} return false;
	}

	public void remplirPiece (Piece p) {
		if (this.piece == null) {
			this.piece = p;
		} else { System.out.println("Erreur : la case est déjà occupée"); }
		
	}

	public void enleverPiece() {
		if (this.piece != null) {
			this.piece = null;
		} else { System.out.println("Erreur : la case est déjà vide"); }
	} 

	public String toString() {
        if(this.piece == null){
            if(this.etat) {
            	return "-";
            } else { return "*"; }
        } return piece.toString().substring(0,1);
    }

    public 

}