public class IP1 {

	public static void main (String[] args) {
		f1();
		System.out.println("\n");
		f2();
		System.out.println("\n");
		System.out.println(java.util.Arrays.toString(f3()));
	}

	public static void f1() {
		for (int i = 0 ; i<6 ; i++) {
			System.out.print("#");
		}
		for (int j = 0 ; j<4 ; j++) {
			System.out.print("\n#");
			for (int k = 0 ; k<4 ; k++) {
				System.out.print("-");
			}
			System.out.print("#");
		}
		System.out.println("");
		for (int l = 0 ; l<6 ; l++) {
			System.out.print("#");
		}	
		System.out.println("");	
	}

	public static void f2() {
		for (int i = 0 ; i<10 ; i++) {
			if (i%2 == 0) {
				for (int j = 0 ; j<6 ; j++) {
					System.out.print("#");
				}
				System.out.println("");
			} else {
				for (int k = 0 ; k<6 ; k++) {
					System.out.print("*");
				}
				System.out.println("");
			}
		}
	}

	public static int[] f3() {
		int[] tab = new int[10];
		tab[0] = 1;
		for (int i = 0 ; i<tab.length ; i++) {
			if (tab[i] == 0) {
				tab[i] = f3Bis(tab);
			}		
		}
		return tab;
	}

	public static int f3Bis (int tab[]) {
		int x = 0;
		for (int i = 0 ; i < tab.length ; i++) {
			x += tab[i];
		}
		return x;
	}
}