import java.util.Random;

public class Plateau {
	private int hauteur, largeur, nbMine, nbDrapeaux;
	private boolean[][] mines;
	private int[][] etats;
	private int[][] adja;

	public Plateau (int ha, int la,	int mi) {
		this.hauteur = ha;
		this.largeur = la;
		this.nbMine = mi;
		this.nbDrapeaux = 0;
		this.mines = new boolean[ha+2][la+2];
		this.etats = new int[ha+2][la+2];
		this.adja = new int[ha+2][la+2];
		ajouteMinesAlea();
		calculeAdjacence();

	}

	/* public void affiche() {
		for (int i = 0 ; i<this.hauteur+2 ; i++) {
			for (int j = 0 ; j<this.largeur+2 ; j++) {
				revelerCase(i, j);
			}
		}
		afficheCourant();
	} */

	public boolean jeuPerdu() {
		for (int i = 1 ; i<this.largeur+1 ; i++) {
			for (int j = 1 ; j<this.hauteur+1 ; j++) {
				if (this.etats[i][j] == 2 && this.mines[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean jeuGagne() {
		boolean b = jeuPerdu();
		if (b) {
			return false;
		}
		for (int i = 0 ; i<this.largeur ; i++) {
			for (int j = 0 ; j<this.hauteur ; j++) {
				if (this.etats[i][j] != 2 && this.mines[i][j] == false) {
					return false;
				}
			}
		}
		return true;
	}

	private void ajouteMinesAlea() {
		Random r = new Random();
		int ha, la;

		for (int i = 0 ; i<this.nbMine ; i++) {
			ha = r.nextInt(this.hauteur);
			la = r.nextInt(this.largeur);
			if (!this.mines[ha][la]) {
				this.mines[ha][la] = true;
			}
			else {
				i--;
			}
		}
	}

	public void drapeauCase(int x, int y) {
		if (this.etats[x][y] == 1) {
			this.etats[x][y] = 0;
			this.nbDrapeaux--;
		} 
		if (this.etats[x][y] == 0) {
			this.etats[x][y] = 1;
			this.nbDrapeaux++;
		}
		else {
			System.out.println("Déja découvert");
		}
	}

	public void revelerCase(int x, int y) {
		if (this.etats[x][y] == 1 || this.etats[x][y] == 2) {
			System.out.println("case déjà vérifiée");
		}
		if (this.etats[x][y] == 0) {
			this.etats[x][y] = 2;
		}
	}

	private void calculeAdjacence() {
		
		/*for (int i = 0 ; i<this.largeur+2 ; i++) {
			this.adja[0][i] = -1;
			//this.adja[this.largeur+1][i] = -1;
		}
		for (int i = 0 ; i<this.hauteur+2 ; i++) {
			//this.adja[i][this.hauteur+1] = -1;
			this.adja[i][0] = -1;
		} */

		for (int i = 1 ; i<this.hauteur+1 ; i++) {
			for (int j = 1 ; j<this.largeur+1 ; j++) {
				if (this.mines[i+1][j] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i][j+1] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i-1][j] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i][j-1] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i+1][j+1] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i-1][j-1] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i-1][j+1] == true) {
					this.adja[i][j]++;
				}
				if (this.mines[i+1][j-1] == true) {
					this.adja[i][j]++;
				}
			}
		}
	}

	public void afficheTout() {
		System.out.println("********************");
		System.out.println("* Mines / Drapeaux *");
		System.out.println("*   " + this.nbMine + "   /   " + this.nbDrapeaux + "      *");
		System.out.println("********************");
		System.out.print("    ");
		int x = 0;
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for (int i = 0 ; i<this.largeur ; i++) {
			System.out.print(i+1 + "  ");
			x++;
		}
		System.out.print("\n");
		for (int i = 0 ; i<x ; i++) {
			System.out.print("----");
		}
		System.out.println("");
		for (int i = 0 ; i<this.hauteur ; i++) {
			System.out.print(Character.toString(alphabet[i]) + " | ");
			for (int j = 0 ; j < this.largeur ; j++) {
				if (this.mines[i][j] == true) {
					System.out.print("*  ");
				}
				else {
					System.out.print(this.adja[i][j] + "  ");
				}

			}
			System.out.println("");
		}
		
		
	}

	public void afficheCourant() {
		System.out.println("********************");
		System.out.println("* Mines / Drapeaux *");
		System.out.println("*   " + this.nbMine + "   /   " + this.nbDrapeaux + "      *");
		System.out.println("********************");
		System.out.print("    ");
		int x = 0;
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for (int i = 0 ; i<this.largeur ; i++) {
			System.out.print(i+1 + "  ");
			x++;
		}
		System.out.print("\n");
		for (int i = 0 ; i<x ; i++) {
			System.out.print("----");
		}
		System.out.println("");
		for (int i = 0 ; i<this.hauteur ; i++) {
			System.out.print(Character.toString(alphabet[i]) + " | ");
			for (int j = 0 ; j < this.largeur ; j++) {
				if (this.etats[i][j] == 0) {
					System.out.print(".  ");
				}
				if (this.etats[i][j] == 1) {
					System.out.print("?  ");
				}
				if (this.etats[i][j] == 2) {
					System.out.print(this.adja[i][j] + "  ");
				}
				
			}
			System.out.println("");
		}
		
		
	}
}