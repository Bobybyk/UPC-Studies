public class Suite {
	public static void main (String[] args) {
		System.out.println(java.util.Arrays.toString(suiteU(5)));
		System.out.println(java.util.Arrays.toString(serieU(5)));
		System.out.println(presentSerieU(5, 4	));
	}

	public static int[] suiteU (int x) {
		int[] tab = new int[x];
		if (x >= 0) {
			for (int i = 0 ; i<tab.length ; i++) {
				if (i == 0) {
					tab[i] = 1;
				}
				if (i > 0 && i < 4) {
					tab[i] = tab[i-1] + 1;
				}
				if (i > 3) {
					tab[i] = tab[i-3] + tab[i-1];
				}
			}
		}
		return tab;
	}

	public static int[] serieU (int n) {
		int[] tab = new int[n];
		int x = 0;
		for (int i = 0 ; i<tab.length ; i++) {
			if (i != 0) {
				for (int j = i ; j >= 0 ; j--) {
					tab[i] += tab[j];
				}
			} else {
				tab[i] = 1;
			}
		}
		return tab;
	}

	public static boolean presentSerieU (int n, int x) {
		if (n >= 0) {
			int[] tab = serieU(n);
			for (int i = 0 ; i<tab.length ; i++) {
				if (tab[i] == x) {
					return true;
				}
			}
		}
		return false;
	}
}