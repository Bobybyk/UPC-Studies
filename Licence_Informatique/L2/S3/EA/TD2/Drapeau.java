public class Drapeau {

	public static void triDrapeau (int[] tab) {
		int p = 0;
		int m = 0;
		int g = tab.length-1;

		while (m <= g) {
			switch (tab[m]) {
				case 0: int x = tab[m] ; tab[m] = tab[p]; tab[p] = x ; m+=1; ; p+=1; break;
				case 1: m+=1; break;
				case 2: int y = tab[m] ; tab[m] = tab[g] ; tab[g] = y ; g-=1; break;
			}
			System.out.println(java.util.Arrays.toString(tab));
		}
		
	}

	public static void triDrapeauBicolore (int[] tab) {
		int m = 0;
		int p = 0;
		int g = tab.length-1;
		int x = 0;

		while (m <= g) {
			switch(tab[m]) {
				case 0 : x = tab[m] ; tab[m] = tab[p] ; tab[p] = x ; m++ ; p++ ; break ;
				case 1 : x = tab[m] ; tab[m] = tab[g] ; tab[g] = x ; g-- ; break;
			}
			System.out.println(java.util.Arrays.toString(tab));
		}


	}

	public static void main (String[] args) {
		int[] t = {1, 1, 0, 2, 0, 2};
		triDrapeau(t);
		System.out.println("----------");
		int[] t2 = {1, 0, 0, 1, 0, 1, 1};
		triDrapeauBicolore(t2);
	}
}