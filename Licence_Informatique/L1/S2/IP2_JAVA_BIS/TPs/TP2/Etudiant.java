public class Etudiant {
	final String prenom, nom;
	double note;

	static int nombreDEtudiants = 0;
	static double sommeDesNotes = 0;

	public Etudiant (String prenom, String nom, double note) {
		this.prenom = prenom;
		this.nom = nom;
		this.note = note;
		nombreDEtudiants++;
		sommeDesNotes += note;
	}

	public void afficher() {
		System.out.println("Nom : " + this.nom + ", prenom : " + this.prenom + ", note : " + this.note);
	}

	public boolean estAdmis() {
		if (this.note < 10) {
			return false;
		} else {
			return true;
		}
	}

	public static double moyenne() {
		return sommeDesNotes/nombreDEtudiants;
	}

	public boolean meilleurQueLaMoyenne() {
		if (moyenne() < this.note) {
			return true;
		} else {
			return false;
		}
	}

	public void modifierNote (double nouvelleNote) {
		sommeDesNotes -= this.note;
		sommeDesNotes += nouvelleNote;
		this.note = nouvelleNote;
	}

	public double getNote() {
		return this.note;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getNom() {
		return this.nom;
	}

}