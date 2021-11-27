public class Tri {
	
	public static void main (String[] args) {
		char[][] t = {{'d', 'e', 'a'},{'b', 'd', 'i'}};
		doubleT(t);
	}

	public static char[][] doubleT (char[][] t) {
		
		System.out.println(java.util.Arrays.deepToString(t));
		
		char c;

		for (int i = 0 ; i<t.length ; i++) {
			for (int j = 0 ; i<t.length ; i++) {
				if (t[i][i] < t[j][i]) {
					c = t[i][j];
					t[j][i] = c;
					t[i][j] = t[j][i];
				}
			}
		}
		System.out.println(java.util.Arrays.deepToString(t));

		return t;
	} 

}