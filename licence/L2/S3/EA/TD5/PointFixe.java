/* 
Q1 : T = [-1, 0, 1, 3, 4, 8]
t[0] = -1 : Pré Point Fixe
t[1] = 0 : Pré Point Fixe
t[2] = 1 : Pré Point Fixe
t[3] = 3 : Point Fixe
t[4] = 4 : Point Fixe
t[5] = 8 : Post Point Fixe
*/

/*
Q2 : La somme des pré-points et post-points fixes est supérieure ou égale à la largeur du tableau.
La soustraction de la somme des pré-points et post-points fixes par la somme des points fixes est égale à la taille du tableau.
*/

/*
Q3 : L'algorithme iter fait, dans le pire des cas, t.length-1 comparaisons
*/

public class PointFixe {

	public static boolean iter (int[] t) {
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] == i) {
				return true;
			}
		}
		return false;
	}

	public static void main (String[] args) {
		int[] t = {-1, 0, 1, 3, 4, 8};
		System.out.println(iter(t));
	}

}