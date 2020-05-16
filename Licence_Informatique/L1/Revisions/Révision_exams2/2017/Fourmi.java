public class Fourmi {
	public static void main (String[] args) {
		int[][] grille = creerGrille(5,5);
		afficheGrille(grille);
		int[] pos = {2,3};
		System.out.println(nouvelleOrientation(grille,pos,2));
	}

	public static int[][] creerGrille (int n, int m) {
		int[][] tab = new int[n][m];
		return tab;
	}

	public static void afficheGrille (int[][] gr) {
		for (int i = 0 ; i<gr.length ; i++) {
			for (int j = 0 ; j<gr.length ; j++) {
				System.out.print(gr[i][j]);
				System.out.print("");
			}
			System.out.println("");
		}
	}

	public static int nouvelleOrientation (int[][] gr, int[] pos, int o) {
		if (gr[pos[0]][pos[1]] == 0) {
			return o-1;
		} else {
			return o+1;
		}
	}

	public static int[] avanceEtChange (int[][] gr, int[] pos, int o) {
		int[] tab;
		if (o == 0) {
			if (pos[0] == 0) {
				pos[0] = 4;
				return pos;
			}
		}
	}
}