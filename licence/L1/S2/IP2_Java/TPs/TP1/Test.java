public class Test {
	

	public static void main (String[] args) {

		Fruit f = new Fruit ("pamplemousse", 330);
		Fruit g = new Fruit ("pamplemousse", 330);
		Fruit h = f;
		// Les variables f et g sont deux variables différentes contrairement à h. Ces trois variables pointes vers des objets Fruit.
		Fruit.afficher(f);
		Fruit j = Fruit.hybridation(f, g);
		Fruit.afficher(j);
		System.out.println("Fin Test");
	}
}