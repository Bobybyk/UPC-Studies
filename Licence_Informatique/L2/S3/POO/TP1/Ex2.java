public class Ex2 {

	public static void main (String[] args) {
		Ex2 a = new Ex2();
		int[] t1 = {1,2,3,4,5};
		int[] t2 = {4, 12};
		//a.affiche(t1);
		a.affiche(a.multiplication(t1, t2));
		a.affiche(a.split(t2));
	}

	public Ex2() {

	}

	public void affiche (int[] t) {
		for (int i = 0 ; i<t.length ; i++) {
			System.out.print(t[i] + ", ");
		}
		System.out.println("");
	}

	public int[] multiplication (int[] t1, int[] t2) {
		int x = t1.length < t2.length?t2.length:t1.length;
		int a, b;
		int[] t = new int[x];
		for (int i = 0 ; i<t.length ; i++) {
			if (i+1 > t1.length) {
				a = 1;
			} else { a = t1[i]; }
			if (i+1 > t2.length) {
				b = 1;
			} else { b = t2[i]; }
			t[i] = a*b;
		}
		return t;
	}

	public int[] split (int[] t) {
		String s = " ";
		for (int i = 0 ; i < t.length ; i++) {
			s += String.valueOf(t[i]);
		}
		int []tab = new int [s.length()];
		for (int i = 0 ;i < s.length() ; i++) {
			tab[i] = Character.getNumericValue(s.charAt(i));
		}
		return tab;
	} 

}