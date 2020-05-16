public class Tabl {
	public static void main (String[] args) {
		remplir(5);
	}

	public static void remplir (int x) {
		int[] tab = new int[x];
		int y = 0;
		for (int i = 0 ; i<tab.length ; i++) {
			y++;
			tab[i] = y; 
		}
		System.out.println(java.util.Arrays.toString(tab));
		System.out.println(tousDifferents(tab));
	}

	public static boolean tousDifferents (int[] tab) {
		for (int i = 0 ; i<tab.length ; i++) {
			for (int j = i+1 ; j<tab.length ; j++) {
				if (tab[i] == tab[j]) {
					return false;
				}
			}
		}
		return true;
	}
}