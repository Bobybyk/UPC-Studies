public class tableaux {
	

	public static void tab() {

		int tab1[] = new int[5];

		int tab2[] = {1, 5, 8, 12, 22};

		for (int i = 0 ; i<tab1.length ; i++) {

			tab1[i] = i;

		}

		System.out.println(java.util.Arrays.toString(tab1));
		System.out.println(java.util.Arrays.toString(tab2));

		int tab3[][] = new int[5][5];

		int tab4[][] = {{5, 8, 6, 42, 20} , {20, 25,22}};

		for (int j = 0 ; j<tab3.length ; j++) {
			for (int k = 0 ; k<tab3.length ; k++) {
				tab3[j][k] = j+k;
			}
		}


		System.out.println(java.util.Arrays.deepToString(tab3));
		System.out.println(java.util.Arrays.deepToString(tab4));

	}


	public static void main (String[] args) {

		tab();

	}

}