public class Suite2 {
	public static void main (String[] args) {
		System.out.println(f1(100));
	}

	public static int f1 (int x) {
		int y = 0;
		while(y < x) {
			if (y%2 == 0) {
				y--;
			} else {
				y = (y+1)*3;
			}
		}
		return y;
	}
}