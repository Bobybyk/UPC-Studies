public class Robot {
	private final char nom;
	private int energie;
	private final String texte;

	public Robot (char nom, String paroles) {
		this.nom = nom;
		this.energie = 10 + (int)(Math.random()*11);
		this.texte = paroles;
	}

	public String description(){
		return "Robot " + this.nom + " dit " + this.texte + " quand il parle à " + energie + " points d'énergie";
	} 

	public boolean nomCorrect() {
		
		if (this.nom <= 'Z' && this.nom >= 'A') {
			return false;
		}
		return true;
	}

	public void chante () {

		int x = ((this.nom) - 'a') + 1;

		for (int i = 0 ; i<x ; i++) {
			System.out.println(this.texte);
		}

		if (this.energie > 10) {
			this.energie = energie - 10;
		}
	}

	public char getNom () {
		return this.nom;
	}
	public int getEnergie() {
		return this.energie;
	}
	public String getTexte() {
		return this.texte;
	}
}