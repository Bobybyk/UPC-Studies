public class Fruit {

	public String nom; //le nom du fruit
	public int poids; //le poids du fruit

	public Fruit (String n, int p) {
		nom = n;
		poids = p;
	}

	public static void afficher (Fruit f) {
		
		System.out.println("Ce fruit est un " + f.nom + " qui pèse " + f.poids + " g.");
	
	}

	static Fruit hybridation (Fruit f1, Fruit f2) {
		
		String x = f1.nom + f2.nom;
		int y = f1.poids + f2.poids;

		Fruit hy = new Fruit (x, y);

		return hy; 

	} 

}

