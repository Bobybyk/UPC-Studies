public class sudoku {

	
	public static int[][] grilleSimple () {

		int[][] tabS = new int [9][9];
		int x = 0;
		int y = 0;
		for (int i = 0 ; i<tabS.length ; i++) {
			if (x != 0) {
				y = y + 3;
				x = 0 + y;
			}
			for (int j = 0 ; j<tabS.length ; j++) {
				x++;
				if (x > 9) {
					x = 1;
				}
				tabS[i][j] = x;
			} 
		}

		return tabS;
	}


	public static void main (String[] args) {

		System.out.println(java.util.Arrays.deepToString(grilleSimple()));

	}
}