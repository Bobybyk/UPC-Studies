public class Add {	

	public Add (int[] tabAdd) {

		System.out.println("class Add : j'additionne les termes de tab1");
		int x = 0;
		for (int j = 0 ; j<tabAdd.length ; j++) {
			x = x + tabAdd[j];
			System.out.println("class Add : x = x + tabAdd[j] : x = " + x);
			tabAdd[j] = 0;
			System.out.println("class Add : tabAdd[j] = 0 : " + java.util.Arrays.toString(tabAdd));
		}
		tabAdd[0] = x;
		System.out.println("class Add : tabAdd[0] = x : " + java.util.Arrays.toString(tabAdd));
	}
}