public class Test {

	public static void main (String[] args) {
		Automate l = new Automate();
		l.initialisation();
		l.nEtapes(4);

		System.out.println("\n----------\n");
		Automate a = new Automate("###---");
		a.affiche();
	}
}