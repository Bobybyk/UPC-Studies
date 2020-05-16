public class Tab {

	public static void main(String[] args) {
		int[] tab = tab1(5);
		System.out.println(java.util.Arrays.toString(tab));
		System.out.println(java.util.Arrays.deepToString(tab2(tab)));
	}

	public static int[] tab1(int x) {
		int[] t = new int[x];
		for (int i = 0 ; i<t.length ; i++) {
			t[i] = i+1;
		}
		return t;
	}

	public static int[][] tab2 (int[] tab) {
		int[][]t = new int[tab.length][tab.length];
		for (int i = 0 ; i<t.length ; i++) {
			for (int j = 0 ; j<t.length ; j++) {
				t[i][j] = i+j;
			}
		}
		return t;
	}

}