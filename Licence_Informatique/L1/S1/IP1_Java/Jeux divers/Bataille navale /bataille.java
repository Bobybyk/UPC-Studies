import java.util.Random;

public class bataille {

	public static int [][]gridComp = new int [10][10];
	public static int [][]gridPlay = new int [10][10];
	public static int [][]grille = new int [10][10];	// init grids


	public static boolean posOk(int [][]grille, int l, int c, int d, int t) {

		int e = c*l;

		if (grille[l][c] == 0) {
			grille[l][c] = t;
			return true;
			}
		return false;

	}


	public static void initGridComp() {

	}

	public static void main (String[] args) {

		Random rand = new Random();	

		int l = rand.nextInt(10) + 0;	// give the boxe line most on the left of the boat.
		int c = rand.nextInt(10) + 0;	// give the boxe column most on the right of the boat.
		int  d = rand.nextInt(2) + 1;  // 1 for horizontal & 2 for vertical  
		int t = rand.nextInt(4) + 2;	// give boxes number of a boat

		System.out.println((java.util.Arrays.deepToString(grille)));
		System.out.println(l);
		System.out.println(c);
		System.out.println(d);
		System.out.println(t);	

		System.out.println(posOk(grille, l, c, d, t));
		System.out.println((java.util.Arrays.deepToString(grille)));
	}



}