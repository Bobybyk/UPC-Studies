public class Utilisateur {
	
	private String pseudo;
	private String motDePasse;
	private final String adresseMail;

	public Utilisateur (String pse, String mot, String adre) {

		this.pseudo = pse;
		this.motDePasse = mot;
		this.adresseMail = adre;

	}

	public boolean testMotDePasse (String ancien) {

		if ((this.motDePasse).equals(ancien)) {
			return true;
		}
		return false;
	}

	void changerMotDePasse (String motdePasse, String nouveauMotDePasse) {

		if ((testMotDePasse(motdePasse)) == true) {
			this.motDePasse = nouveauMotDePasse;
		} 

	}

	public String getPseudonyme () {
		return this.pseudo;
	}

	public void setPseudonyme (String set) {
		this.pseudo = pseudo;
	}

}