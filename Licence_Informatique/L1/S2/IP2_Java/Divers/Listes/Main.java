public class Main {
	
	public static void main (String[] args) {

		Objets e1 = new Objets ("eleve 1", 10);
		Objets e2 = new Objets ("eleve 2", 0);
		Objets e3 = new Objets ("eleve 3", 2);
		Objets e4 = new Objets ("eleve 4", 18);
		Objets e5 = new Objets ("eleve 5", 4);
		Objets e6 = new Objets ("eleve 6", 1);
		Objets e7 = new Objets ("eleve 7", 20);
		Objets e8 = new Objets ("eleve 8", 15);
		Objets e9 = new Objets ("eleve 9", 12);
		Objets e10 = new Objets ("eleve 10", 9);

		Classe c = new Classe();

		c.prendreTete(e1);
		c.prendreTete(e2);
		c.ajouterEleve(e2);
		c.prendreTete(e3);
		c.ajouterEleve(e3);
		c.prendreTete(e4);
		c.ajouterEleve(e4);
		c.prendreTete(e5);
		c.ajouterEleve(e5);
		c.prendreTete(e6);
		c.ajouterEleve(e6);
		c.prendreTete(e7);
		c.ajouterEleve(e7);
		c.prendreTete(e9);
		c.ajouterEleve(e8);
		c.prendreTete(e9);
		c.ajouterEleve(e9);
		c.prendreTete(e10);
		c.ajouterEleve(e10);

		c.affichage();
	}
}