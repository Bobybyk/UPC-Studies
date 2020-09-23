public class Mediane {


	public static int compteInf (int[] t, int x) {
		int c = 0;
		for (int i = 0 ; i<t.length ; i++) {
			if (t[i] < x) {
				c++;
			}
		}
		return c;
	}

	public static int findMed (int[] t) {
		if (t.length%2 == 0) {
			return 0;	// return 0 si t impair
		}
		for (int i = 0 ; i<t.length ; i++) {
			for (int j = i+1 ; j<t.length ; j++) {
				if (t[i] == t[j]) {
					return 0;
				}
			}
		}
		for (int i = 0 ; i<(t.length-1) / 2 ; i++) {
			if (compteInf(t, t[i]) == (t.length-1)/2) {
				System.out.println("t[i]");
				return t[i];
			}
		}
		System.out.println("Erreur");
		return 0;


	}

	public static void main (String[] args) {
		int[] t = {50, 5, 2, 12, 24, 48, 1};
		System.out.println(findMed(t));
		
	}
}