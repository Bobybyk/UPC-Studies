public class Tour {
	public static Noeud[] tour = new Noeud[1];

	public Tour (Noeud n) {
		
		if (tour[0] == null) {
			tour[0] = n;
		} else {
			Noeud[] t = new Noeud[tour.length + 1];
			int x = 0;
			for (int i = 0 ; i < tour.length ; i++) {
				t[i] = tour[i];
				x = i;
			}
			t[x+1] = n;
			tour = t;
		}
	}

	public static void afficher() {
		System.out.println(java.util.Arrays.toString(tour));
	}


}