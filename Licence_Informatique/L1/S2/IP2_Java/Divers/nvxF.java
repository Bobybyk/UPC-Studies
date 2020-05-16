import java.util.Scanner;

public class nvxF {

	public static void main (String[] args) {


		Personne p0001 = new Personne();

		Personne[] annuaire;

		p0001.nom = "Le Franc";
		p0001.prenom = "Matthieu";
		p0001.age = 19;

		System.out.println("Bonjour, " + p0001.prenom + " " + p0001.nom + ", " + p0001.age + "ans.");

		String jour;
		Scanner in = new Scanner(System.in);

		int num = in.nextInt();

		switch (num) {
			case 1: jour = "Lundi"; break;
			case 2: jour = "Mardi"; break;
			case 3: jour = "Mercredi"; break;
			case 4: jour = "Jeudi"; break;
			case 5: jour = "Vendredi"; break;
			case 6: jour = "Samedi"; break;
			case 7: jour = "Dimanche"; break;
			default: jour = "Y'a que 7 jours dans la semaine, sale batard	.";
		}

		System.out.println(jour);

	 }

}

class Personne {
	
	String prenom = "";
	String nom = "";
	int age = 0;

}