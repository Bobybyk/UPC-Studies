public class Utilisateur {
	private String pseudo;
	private String motDePasse;
	private final String adresseMail;

	public Utilisateur (String p, String m, String a) {
		this.pseudo = p;
		this.motDePasse = m;
		this.adresseMail = a;
	}

	public boolean testMotDePasse (String test) {
		if (this.motDePasse.equals(test)) {
			return true;
		} else {
			return false;
		}
	}

	public void changerMotDePasse (String motDePasse, String nouveauMotDePasse) {
		if (testMotDePasse(motDePasse)) {
			this.motDePasse = nouveauMotDePasse;
			System.out.println("Nouveau mot de passe enregistré");
		} else {
			System.out.println("Mot de passe éronné");
		}
	}

	public String getPseudonyme() {
		return this.pseudo;
	}
	public void setPseudonyme (String s) {
		this.pseudo = s;
	}
}