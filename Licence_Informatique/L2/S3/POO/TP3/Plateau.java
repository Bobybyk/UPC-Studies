public class Plateau {
	private int longueur, largeur;
	private Case[][] tCase;

	public Plateau (int longueur, int largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.tCase = new Case[longueur][largeur];
		for (int i = 0 ; i<longueur ; i++) {
			for (int j = 0 ; j<largeur ; j++) {
				if ((i+j)%2 == 0) {
					this.tCase[i][j] = new Case(true);
				}
				else {
					this.tCase[i][j] = new Case(false);
				}
			}
		}
	}

	public boolean horsLimite (int x, int y) {
		if (this.tCase[x][y] == null) {
			return true;
		} return false;
	}

    public Case getCase (int x, int y) {
    	return this.tCase[x][y];
    }

    public void videCase(int x, int y) {
    	this.tCase[x][y].enleverPiece();
    }

    public void remplirCase (int x, int y, Piece p) {
    	this.tCase[x][y].remplirPiece(p);
    }

    public void afficher() {
    	for (int i = 0 ; i<this.longueur ; i++) {
    		for (int j = 0 ; j<this.largeur ; j++) {
    			System.out.print(this.tCase[i][j].toString() + " ");
    		}
    		System.out.println("");
    	}
    } 

    public Case[][] getCase() {
    	return this.tCase;
    }
}