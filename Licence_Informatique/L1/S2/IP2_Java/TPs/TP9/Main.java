public class Main {
	
	public static void main (String[] args) {

		Noeud a = new Noeud (6, null, new Noeud(8));
		Noeud b = new Noeud (9, new Noeud(2), a);
		Noeud c = new Noeud (5, b, null);
		Noeud d = new Noeud (1, new Noeud(4), null);
		Noeud e = new Noeud (7, new Noeud(0), d);
		Noeud f = new Noeud (3, c, e);

		System.out.println("\n#########################");
		System.out.print("Forme infixe : "); 
		f.afficheInfixe();
		System.out.print("\nForme prefixe : ");
		f.affichePrefixe();
		System.out.print("\nForme postfixe : ");
		f.affichePostfixe();
		System.out.println("\n");

		System.out.println("Nombre de noeuds = " + f.nbNoeuds());

		System.out.println("Somme des noeuds = " + f.somme());

		System.out.println("Profondeur this = " + f.profondeur());

		System.out.println("e présent ? " + f.recherche(6));

		Noeud copie = new Noeud (f);
		System.out.println("#########################\n");
	}
}