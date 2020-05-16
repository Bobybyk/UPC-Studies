public class Main {
	
	public static void main (String[] args) {
		Noeud a = new Noeud (1, 10);
		Noeud b = new Noeud (8, 10);
		Noeud c = new Noeud (7, a, b, 10);
		Noeud d = new Noeud (9, 10);
		Noeud e = new Noeud (9, 10);
		Noeud f = new Noeud (2, d, e, 10);
		Noeud g = new Noeud (9, f, c, 10);

		System.out.println(g.longueur());
		//g.remplir(0);
		System.out.println("");
		//g.descente();
		System.out.println("");
		System.out.println(g.profondeur());
		//g.graph();
		//g.afficher();
	}
}