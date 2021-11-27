public class Mem {
	int[] tab;

	public static int rapide (int n) {
		tab = new tab [n+1];
		return rapideAux(n);
	}

	static int rapideAux (int n) {
		if (tab[n] == 0) {
			switch(n) {
				case 0:
				case 1: tab[n] = 1; break;
				case 2: tab[n] = 2; break;
				default:tab[n] = rapideAux(n-1) + rapideAux(n-2) + rapideAux(n-3); break;
			}
		}
		return tab[n];
	}
}