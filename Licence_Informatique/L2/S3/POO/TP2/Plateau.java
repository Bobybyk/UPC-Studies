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

	}

	public void affiche() {
		System.out.println(java.util.Arrays.deepToString(this.mines));
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

	private void calculeAdjacence() {
		int x = 0;
		for (int i = 1 ; i<this.mines.length ; i++) {
			for (int j = 0 ; j<this.mines.length ; j++) {
				if (this.mines[i+1][j] == true) {
					x++;
				}
				if (this.mines[i][j+1] == true) {
					x++;
				}
				if (this.mines[i-1][j] == true) {
					x++;
				}
				if (this.mines[i][j-1] == true) {
					x++;
				}
				if (this.mines[i+1][j+1] == true) {
					x++;
				}
				if (this.mines[i-1][j-1] == true) {
					x++;
				} 
				if (this.mines[i-1][j+1] == true) {
					x++;
				}
				if (this.mines[i+1][j-1] == true) {
					x++;
				}
			}
		}
	}
}