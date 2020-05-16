public class Grille {
	public int largeur, longueur;
	public boolean[][] couleur;

	public Grille (int la, int lon) {
		this.largeur = la;
		this.longueur = lon;
		this.couleur = new boolean[la][lon];

	}

	public void dessine() {
		for (int i = 0 ; i<this.couleur.length ; i++) {
			for (int j = 0 ; j<this.couleur.length ; j++) {
				if (j%this.largeur == 0) {
					System.out.println("");
				}
				if (this.couleur[i][j] == true) {
					System.out.print(" ");
				}
				if (this.couleur[i][j] == false) {
					System.out.print("#");
				} 
			
			}
		}
		System.out.println("");
	}

	public void dessineAvecFourmi(Fourmi f) {
		for (int i = 0; i<this.couleur.length ; i++) {
			for (int j = 0; j<this.couleur.length ; j++) {
				if (j%this.largeur == 0) {
					System.out.println("");
				}
				if (this.couleur[i][j] == true && f.getX()*f.getX() != i*j) {
					System.out.print("_");
				}
				if (this.couleur[i][j] == false && f.getX()*f.getX() != i*j) {
					System.out.print("#");
				} 
				if (f.getX() == i && f.getY() == j) {
					System.out.print("0");
				}
			}
		}		
	}

	public boolean getCouleur (int x, int y) {
		return this.couleur[x][y];
	}

	public void changeCouleur(int x, int y) {
		this.couleur[x][y] = this.couleur[x][y] == true ? false:true;
	}

	public boolean estHorsGrille (int x, int y) {
		if (x > 0 || y < 0 || x*y > this.largeur*this.longueur) {
			return false;
		} else {
			return true;
		}
	}
}