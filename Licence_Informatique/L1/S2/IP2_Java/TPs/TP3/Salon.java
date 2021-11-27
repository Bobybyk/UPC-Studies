public class Salon {
	
	Utilisateur[] tUtilisateur;
	Message[] tMessage;
	String [] historique;
	public int nU = 0;
	public int nM = 0;
	public int nH = 0;

	public Salon () {

		this.tUtilisateur = null;
		this.tMessage = null;
		this.historique = null;

	} 

	public void ajouterUtilisateur (Utilisateur u) {

		Utilisateur [] T = new Utilisateur [nU+1];
		T[nU] = u;

		this.tUtilisateur = T;
		this.nU = (this.nU)+1;

	}

	public boolean estPresent (Utilisateur u1) {

		for (int i = 0 ; i<tUtilisateur.length ; i++) {
			if (tUtilisateur[i] == u1) {
				System.out.println(u1 + " est présent.");
				return true;
			}
		}
		return false;
	}

	public void ajouterMessage (Message message) {

			if (estPresent(message.getUser())) {

				Message [] tMes = new Message [nM+1];

				tMes[nM] = message;

				this.tMessage = tMes;
				this.nM = (this.nM)+1;

				System.out.println(java.util.Arrays.toString(tMessage));
			}
		}

/*	public void afficher () {

		String [] t = new String [nH+1];
		String pseudo;
		String message;

		for (int i = 0 ; i<(this.tUtilisateur).length ; i++) {
			for (int j = 0 ; j<(this.tMessage).length ; j++) {
				pseudo = this.tUtilisateur[i].pseudo;
				message = this.tMessage[j].message;
				t[nH] = pseudo + " : " + message;
				this.historique = t;
				this.nH = (this.nH)+1;

			}
		}

		for (int k = 0 ; k<(this.historique.length) ; k++) {
			System.out.println(this.historique[k]);
		}

	}*/

}