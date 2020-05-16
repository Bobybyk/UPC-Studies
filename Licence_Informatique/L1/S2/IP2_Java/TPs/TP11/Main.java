public class Main {
	
	public static void main (String[] args) {

		Noeud a = new Noeud ("+", new Noeud("1"), new Noeud("2"));
		Noeud b = new Noeud ("/", new Noeud("6"), new Noeud("3"));
		Noeud c = new Noeud ("-", b, new Noeud("4"));
		Noeud d = new Noeud ("*", a, c);
		Noeud e = new Noeud ("-", new Noeud("0"), d);

		System.out.println("\n#########################");
/*		System.out.print("Forme infixe : "); 
		f.afficheInfixe();
		System.out.print("\nForme prefixe : ");
		f.affichePrefixe();
		System.out.print("\nForme postfixe : ");
		f.affichePostfixe();
		System.out.println("\n");

		System.out.println("Nombre de noeuds = " + f.nbNoeuds());

		System.out.println("Somme des noeuds = " + f.somme());

		System.out.println("Profondeur this = " + f.profondeur());

		System.out.println("e présent ? " + f.recherche("6"));	*/

		System.out.println(e.verif());
		e.afficheExpression();

		System.out.println("\n#########################\n");
	}
}