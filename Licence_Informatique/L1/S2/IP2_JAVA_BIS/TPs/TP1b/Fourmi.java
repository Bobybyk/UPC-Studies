public class Fourmi {
	public int x, y, orientation;
	public Grille gril;

	public Fourmi (int x, int y, int o, Grille g) {
		this.x = x;
		this.y = y;
		this.orientation = o;
		this.gril = g;
	}

	public void afficheToi() {
		System.out.println("X = " + this.x + ", Y = " + this.y + ", orientation = " + this.orientation);
	}

	public void tourne (boolean aGauche) { //3 : gauche ; 1 : droite
		if (aGauche == true) {
			this.orientation -= 1;
			if (this.orientation < 0) {
				this.orientation = 0;
			}
		}
		if (aGauche == false) {
			this.orientation += 1;
			if (this.orientation > 3) {
				this.orientation = 3;
			}
		}
	}

	public void unPas() {
		if (this.orientation == 0) {
			this.y -= 1;
		}
		if (this.orientation == 1) {
			this.x += 1;
		}
		if (this.orientation == 2) {
			this.y += 1;
		}
		if (this.orientation == 3) {
			this.x -= 1;
		}
	}

	public boolean unMouvement() {
		if (gril.estHorsGrille(this.x, this.y)) {
			return false;
		}
		this.tourne(gril.getCouleur(this.x, this.y));
		gril.changeCouleur(this.x, this.y);
		this.unPas();
		return true;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}