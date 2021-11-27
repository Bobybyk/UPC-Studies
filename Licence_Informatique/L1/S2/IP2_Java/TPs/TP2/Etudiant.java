public class Etudiant {
	
	final String prenom;
	final String nom;
	double note;

	static int nombreDEtudiants = 0;
	static double sommeDesNotes = 0;

	Etudiant (String prenom, String nom, double note) {

		this.prenom = prenom;
		this.nom = nom;
		this.note = note;

		nombreDEtudiants ++;
		sommeDesNotes = sommeDesNotes + note;

	}

	void afficher() {

		System.out.println(this.nom + " " + this.prenom + " : " + this.note);

	}

	boolean estAdmis() {

		if (this.note < 10) {
			return false;
		}
		return true;
	}

	static double moyenne(Etudiant e1, Etudiant e2, Etudiant e3) {

		double moy = (e1.note + e2.note + e3.note) / 3;

		return moy;

	}

	boolean meilleurQueLaMoyenne(double moy) {

		if (this.note < moy) {
			return false;
		}
		return true;

	}

	void modifierNote(double nouvelleNote) {

		sommeDesNotes = sommeDesNotes - this.note;
		this.note = nouvelleNote;
		sommeDesNotes = sommeDesNotes + this.note;

		

	}

}