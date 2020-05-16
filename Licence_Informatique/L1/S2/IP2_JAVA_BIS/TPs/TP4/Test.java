public class Test {
	public static void main (String[] args) {

		System.out.println("\n-------------Exercice 1-------------");
		//1er Groupe de robots
		Robot r1 = new Robot('a', "Dans le port d'Amsterdam");
		Robot r2 = new Robot('b', "Y'a des marins qui chantent");
		Robot r3 = new Robot('c', "Les rêves qui les hantent");
		Robot r4 = new Robot('d', "Au large d'Amsterdam");
		Robot r5 = new Robot('e', "Dans le port d'Amsterdam");
		Robot r6 = new Robot('f', "Y'a des marins qui dorment");
		Robot r7 = new Robot('g', "Comme des oriflammes");
		Robot r8 = new Robot('h', "Le long des berges mornes");
		Robot r9 = new Robot('i', "Dans le port d'Amsterdam...");		

		System.out.println(r1.description());
		System.out.println(r1.nomCorrect());

		System.out.println("\n-------------Exercice 2-------------");

		Groupe g = new Groupe();
		
		//méthode prendre tête
		g.prendreTete(r2);
		g.prendreTete(r1);

		System.out.println("\n-------------Exercice 3-------------");

		//méthode ajouter nouveau
		g.ajouteNouveau(r3);
		g.ajouteNouveau(r4);
		g.ajouteNouveau(r5);
		g.ajouteNouveau(r6);
		g.ajouteNouveau(r7);
		g.ajouteNouveau(r8);
		g.ajouteNouveau(r9);

		//afficher
		g.affiche();
		System.out.println("");

		System.out.println("numérologie : " + g.numerologie());
		System.out.println("BandName : " + g.bandName());
		System.out.println("");

		r3.chante();

		g.chantez();

		System.out.println("\n-------------Exercice 4-------------");

		g.couperAPartirDe('b');
		g.affiche();
	}
}