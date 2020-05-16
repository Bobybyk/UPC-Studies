public class Panier {
	
	public Fruit[] tF;
	public Panier p;

	Panier (Fruit[] f) {
		this.tF = f;
	}

	Panier() {
		this.p = null;
	}

	Panier (Fruit f, Panier p) {

	}

	public static void afficher (Panier p) {
		for (int i = 0 ; i<p.tF.length ; i++) {
			Fruit.afficher(p.tF[i]);
		}
	}
}