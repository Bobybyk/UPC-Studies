public class Main {
	
	public static void main (String[] args) {
		Noeud a = new Noeud(1);
		Noeud b = new Noeud(2);
		Noeud c = new Noeud(3, a, b);
		Noeud d = new Noeud(4);
		Noeud e = new Noeud(5);
		Noeud f = new Noeud(9, d, e);
		Noeud g = new Noeud(12, f, c);

		g.afficheInfixe();
		System.out.println("");
		g.affichePostfixe();
		System.out.println("");
		g.affichePrefixe();
		System.out.println("");
 	}
}