public class Main {

	public static void main (String[] args) {
		Paire<? extends Number, ? extends Number> p = new Paire<Integer, Integer>(1, 10);
		p.setGauche(new Integer(10));
		Paire<? extends Number, ? extends Number> p = new Paire<Integer, Double>(1, 1.5);
	}
}