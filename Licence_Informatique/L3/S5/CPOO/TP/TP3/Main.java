public class Main {

	public static void main(String[] args) {
		try {
			Carre c = new Carre(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pas assez de paramètres données");
			System.exit(1);
		}
		
	}
}