public class Test {

	// Le mot clef static permet de placer les objets qu'il contient dans un espace mémoire commun à tous les objets de la classe. Donc normalement accessibles seulement depuis la classe en question.
	// Le mot clef final permet de définir une variable qui ne pourra être modifié ensuite.
	// Les attributs de la classe Etudiant ne sont pas statique, il ne peuvent donc pas être "vus" dans le constructeur.

	public static void main (String[] args) {

		Etudiant e1 = new Etudiant ("Luke", "Skywalker", 8.25);
		Etudiant e2 = new Etudiant ("Leia", "Organa", 11.75);
		Etudiant e3 = new Etudiant ("Matthieu", "Le Franc", 18);

		System.out.println("nb d'etudiant : " + Etudiant.nombreDEtudiants);

		System.out.println("somme des notes : " + Etudiant.sommeDesNotes);

		e1.afficher();
		e2.afficher();
		e3.afficher();

		System.out.println(e1.estAdmis());
		System.out.println(e2.estAdmis());
		System.out.println(e3.estAdmis());

		double moy = (Etudiant.moyenne(e1, e2, e3));
		System.out.println(moy);

		System.out.println(e1.meilleurQueLaMoyenne(moy));
		System.out.println(e2.meilleurQueLaMoyenne(moy));
		System.out.println(e3.meilleurQueLaMoyenne(moy));

		e2.modifierNote(19.5);
		e2.afficher();

		System.out.println("moyenne : " + Etudiant.moyenne(e1, e2, e3));

		Trio membres = new Trio (e1, e2, e3);

		System.out.println(membres.premier());

		System.out.println(membres.classement("Matthieu", "Le Franc"));

		System.out.println(membres.moyenne());
	}
}