import java.util.Random;

public class G2048b {


public static int [][]grille = new int [3][3];

	public static void initBoard () {

		grille[1][0] = 2;
		grille[2][2] = 2;

		System.out.println((java.util.Arrays.deepToString(grille)));		

	}


	public static boolean isBoardWinning () {

		for (int i = 0 ; i<3 ; i++) {
			for (int j = 0 ; j<3 ; j++) {
				if (grille[i][j] == 2048) {
					return true;
				}
			}
		}
		return false;

	}


	public static int newSquareValue () {

		Random rand = new Random();	
		
		int r = rand.nextInt(100) + 0;

		if (r <89) {
			return 2;
		}

		return 4;
	}


	public static void main (String[] args) {

		initBoard();
		System.out.println(newSquareValue());
		System.out.println(isBoardWinning());
	}

}