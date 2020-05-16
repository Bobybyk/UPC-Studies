public class Etudiant {
	String nom, prenom;
	int num;
	int note;

	public Etudiant (String n, String p, int nu, int no) {
		this.nom = n;
		this.prenom = p;
		this.num = nu;
		this.note = no;
	}

	public static void afficher (Etudiant etu) {
		System.out.println("nom : " + etu.nom + ", Prenom : " + etu.prenom + ", (Numéro étudiant : " + etu.num + ") : Note " + etu.note);

	}

	public static boolean estAdmis (Etudiant etu) {
		if (etu.note > 9) {
			return true;
		}
		return false;
	}

	public static String mention (Etudiant etu) {
		if (etu.note < 10 && etu.note >= 0) {
			return "Ajourné";
		}
		else if (etu.note > 9 && etu.note < 12) {
			return "Passable";
		}
		else if (etu.note > 11 && etu.note < 14) {
			return "Assez bien";
		}
		else if (etu.note > 13 && etu.note < 16) {
			return "Bien";
		}
		else if (etu.note > 15 && etu.note < 21) {
			return "Très bien";
		}
		else {
			return "Note invalide";
		}
	}
}