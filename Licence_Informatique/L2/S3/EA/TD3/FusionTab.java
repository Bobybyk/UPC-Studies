public class FusionTab {

	public static void main (String[] args) {
		int[] t = {2, 9, 4, 6, 7, 10};
		System.out.println(java.util.Arrays.toString(newT(t)));
	}

	public static int[] newT (int[] t) {
		int tmp;
		for (int i = 0 ; i<t.length-1 ; i++) {
			if (t[i+1] < t[i]) {
				tmp = t[i];
				t[i] = t[i+1];
				t[i+1] = tmp;
			}
		}
		return t;
	}
}

// Dans le pire des cas, n-1 comparaisons sont effectuées.