public class Etudiant {
	
	String nom, prenom = "";
	int num = 0;
	int note = 0;

	public static void afficher (Etudiant etu) {

		System.out.println("Nom : " + etu.nom + ", Prenom : " + etu.prenom + ", Numéro d'étudiant : " + etu.num + ", Note : " + etu.note);

	}

	public static boolean estAdmis (Etudiant etu) {

		if (etu.note < 10) {
			return false;
		}
		return true;
	}

	public static String mention (Etudiant etu) {

		if ((etu.note > 15) && (etu.note <= 20)) {
			return "Très bien";
		}
		if ((etu.note < 16) && (etu.note >= 14)) {
			return "Bien";
		}
		if ((etu.note < 14) && (etu.note >= 12)) {
			return "Assez Bien";
		}
		if ((etu.note < 12) && (etu.note >= 10)) {
			return "Passable";
		}
		if ((etu.note < 10) && (etu.note >= 0)) {
			return "Ajournée";
		}
		return "Note invalide";
	}	
}