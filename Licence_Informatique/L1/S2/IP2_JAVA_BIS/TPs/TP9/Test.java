public class Test {

	public static void main (String[] args) {
		Noeud a = new Noeud(6,null ,new Noeud (8));
		Noeud b = new Noeud(9, new Noeud(2), a);
		Noeud c = new Noeud(5, b, null);
		Noeud d = new Noeud(1, new Noeud(4), null);
		Noeud e = new Noeud(7, new Noeud(0), d);
		Noeud f = new Noeud(3, c, e);
		
		System.out.println("Parcours infixe : ");
		f.afficheInfixe();
		System.out.println("");
		System.out.println("------------------");
		f.affichePrefixe();
		System.out.println("");
		System.out.println("------------------");
		f.affichePostfixe();
		System.out.println("");
		System.out.println("------------------");
		System.out.println("Nombre de noeuds = " + f.nbDeNoeuds(0));
		System.out.println("");
		System.out.println("------------------");
		System.out.println("Somme des étiquettes = " + f.somme());
		System.out.println("");
		System.out.println("------------------");
		System.out.println("Profondeur = " + f.profondeur());
		System.out.println("");
		System.out.println("------------------");
		System.out.println("Etiquette présente : " + f.recherche(6));
		System.out.println("");
		System.out.println("------------------");

		Noeud fBis = new Noeud(f);

		System.out.println("Parcours infixe, fBis : ");
		fBis.afficheInfixe();
		System.out.println("");
		System.out.println("------------------");

	}
}