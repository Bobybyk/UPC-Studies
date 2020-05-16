public class Test {
	public static void main (String[] args) {
		System.out.println("\n------------------Exercice 1------------------");
		Etudiant e1 = new Etudiant ("Luke", "Skywalker", 8.25);
		Etudiant e2 = new Etudiant ("Leia", "Organa", 11.75);

		System.out.println("nb d'etudiant : " + Etudiant.nombreDEtudiants);
		System.out.println("somme des notes : " + Etudiant.sommeDesNotes);

		Etudiant e3 = new Etudiant ("Matthieu", "Le Franc", 20);

		System.out.println("nb d'etudiant : " + Etudiant.nombreDEtudiants);
		System.out.println("somme des notes : " + Etudiant.sommeDesNotes);

		e1.afficher();
		e2.afficher();

		System.out.println("Status étudiant : " + e3.estAdmis());
		System.out.println("Moyenne : " + Etudiant.moyenne());
		System.out.println("Supérieur à la moyenne : " + e3.meilleurQueLaMoyenne());

		e2.modifierNote(19.5);
		e2.afficher();
		System.out.println("moyenne : " + Etudiant.moyenne());

		System.out.println("\n------------------Exercice 2------------------");
		Trio t = new Trio(e1, e2, e3);

		System.out.println("Meilleur etudiant : " + t.premier().prenom);
		System.out.println(t.classement("Matthieu", "Le Franc")); //Ne fonctionne pas
		System.out.println("Meilleure moyenne trio : " + t.meilleurQueLaMoyenne());
	}
}

/* 	Exercice 2.1/
		static signifie que l'argument est relatif à la classe et est commun à tous les objets de la classe.
		final signifie que l'argument sera définitivement définie lorsqu'on lui attribuera une valeur. 
*/