public class Compteur {

	public static void main (String[] args) {
		compt(5, 0);
	}

	public static int compt(int x, int y) {
		System.out.println(y);
		if (y < x) {
			return compt(y, x);
		}
		return 0;
	}
}