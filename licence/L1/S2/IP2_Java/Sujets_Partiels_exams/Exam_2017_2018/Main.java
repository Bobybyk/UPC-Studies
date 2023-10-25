public class Main {
	
	public static void main (String[] args) {
		Banco banco = new Banco();
		System.out.println(banco.lectGain());
		System.out.println(banco.lectNulSi());
		banco.grattageGain();
		banco.grattageNulSi();
		System.out.println(banco.lectGain());
		System.out.println(banco.lectNulSi());

		System.out.println("\n############\n");

		// Droite
		Noeud a = new Noeud (true);
		Noeud b = new Noeud (false, null, a);
		Noeud c = new Noeud (true);
		Noeud d = new Noeud (false, c, b);

		//Gauche
		Noeud e =  new Noeud (true);
		Noeud f = new Noeud (true);
		Noeud g = new Noeud (false, e, f);

		//Racine
		Noeud h = new Noeud(true, g, d);

		h.affichage();

		System.out.println("\n############\n");
		int x = 0;
		System.out.println(h.nbCheminsPoids(x));
	}
}