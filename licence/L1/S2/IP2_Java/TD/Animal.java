public class Animal {
	
	static String nom = "";
	static char sexe;
	static int age = 0;
	static double poids = 0;
	static String espece = "";
	static int faim = 0;


	public Animal (String nom, char sexe, int age, double poids, String espece, int faim) {

		this.nom = nom;
		this.sexe = sexe;
		this.age = age;
		this.poids = poids;
		this.espece = espece;
		this.faim = faim;
		if (this.faim < 0) {
			this.faim = 0;
		}
		if (this.faim > 10) {
			this.faim = 10;
		}

	}

	static String description () {

		Animal manchot = new Animal("Rico", 'm', 1129, 30, "manchot", 0);
		String charS = ""; 

		if (manchot.sexe == 'm') {
			charS = "male";
		} else { charS = "femelle"; }
		String desManchot = "Je m'appelle " + manchot.nom + ", je suis un " + manchot.espece + " " + charS + ", j'ai " + manchot.age + " jours et je pese " + manchot.poids + " kg.";

		return desManchot;

	}

	static String descriptionBis() {

		String charS = "";

		if (sexe == 'm') {
			charS = "male";
		} else { charS = "femelle"; }

		int annees = age/365;
		int jours = age - 365*annees;	
		String desManchot = "Je m'appelle " + nom + ", je suis un " + espece + " " + charS + ", j'ai " + annees + " ans et " + jours + " jours et je pese " + poids + " kg.";

		return desManchot;		

	}

	static void nourrir () {

		if (faim == 0) {
			poids = poids*0.1 + poids;
		}

		faim = faim - 1;

		Animal manchot = new Animal(nom, sexe, age, poids, espece, faim);
		
	}

	static int[] lePlusGros (int[] tAni) {

		int[] t = new int[0];

		return t; 
	}

	public static void main (String[] args) {

		System.out.println(description());
		System.out.println(descriptionBis());
		nourrir();
	}
}