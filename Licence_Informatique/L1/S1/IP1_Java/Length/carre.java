public class carre {

	public static boolean carre (int [][]n) {

		boolean b = true;
		int t = n.length;
		for (int i = 0; i<t; i++) {
			if (n[i].length != t) {
				b = false;
			}
		}
		return b;

	}

	public static int[] aplatir (int [][]m) {

		int []t = new int [m.length*m.length];
		int k = 0;
		for (int i = 0; i<m.length ; i++) {
			for (int j = 0 ; j<m[i].length ; j++) {
				t[k] = m[i][j];
				k = k + 1;
			}
		}
		return t;
	} 

	public static boolean domaine (int []t) {

		boolean test = true;
		for (int i = 0 ; i<t.length  ; i ++) {
			if (t[i] < 1 || t[i] > t.length) {
				test = false;   
			}
		}
	return test;
	}

	public static boolean diff (int []t) {

		boolean test = true;
		for (int i = 0 ; i<t.length ; i++) {
			for (int j = i+1 ; j<t.length ; j ++) {
				if (t[j] == t[i]){
					test = false;
				}
			}
		}
		return test;
	}

	public static boolean magique (int [][]t) {

		boolean b = true;
		if (carre(t) == false) {
			b = false;
		} else if (domaine(aplatir(t)) == false) {
			b = false;
		} else if (diff(aplatir(t)) == false) {
			b = false;
		} else {
			int r = somDiag(t);
			for (int i = 0 ; i<t.length ; i ++) {
				if (r != somLig(t,i) || r != somCol(t,i)) {
					b = false;
				}
			}
			if (r != somDiagInv(t)) {
				b = false;
			}
		}
		return b;
	}

	public static void main (String[] args) {

		int[][] test = {{1,2,3},{4,5,6},{7,8,9}};
		int b = aplatir(a);
		System.out.println(carre(test));
		System.out.println(java.util.Arrays.toString(aplatir(test)));
		System.out.println(diff(b));
		

	}
}