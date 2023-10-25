public class Conditions {

    public static int absolute (int x) {
	
	if (x > 0) {
		return x;
	}
	else {
		return -x;
	} 

	}

    public static void solve (int x, int y, int z) {

	if (x+y=z) {
		System.out.println("x + y = z");
	}
	if (x - y = z) {
		System.out.println("x - y = z");
	}
	if (- x + y = z) {
		System.out.println("- x + y = z");
	}
	else { System.out.println("Rien du tout !"); }
	

}

    public static void main(String[] args) {

	int x = Integer.parseInt(System.console().readLine());
	int y = Integer.parseInt(System.console().readLine());
	int z = Integer.parseInt(System.console().readLine());
        solve();

    }
}
