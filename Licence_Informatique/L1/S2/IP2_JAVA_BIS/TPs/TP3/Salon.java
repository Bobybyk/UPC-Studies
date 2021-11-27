public class Salon {
	private Message[] message;
	private Utilisateur[] utilisateur;

	public Salon() {
		this.message = null;
		this.utilisateur = null;
	}

	public void ajouterUtilisateur (Utilisateur u) {
		Utilisateur[] uti;
		if (this.utilisateur == null) {
			uti = new Utilisateur[1];
			uti[0] = u;
		} else {
			uti = new Utilisateur[this.utilisateur.length + 1];
			for (int i = 0 ; i<this.utilisateur.length ; i++) {
				uti[i] = this.utilisateur[i];
			}
			uti[uti.length-1] = u;
		}
		this.utilisateur = uti;
	}

	public boolean estPresent (Utilisateur u) {
		for (int i = 0 ; i<this.utilisateur.length ; i++) {
			if (utilisateur[i] == u) {
				return true;
			}
		} return false;
	}

	public void ajouterMessage (Message m) {
		if (estPresent(m.getUtilisateur())) {
			Message[] mess;
			if (this.message == null) {
				mess = new Message[1];
				mess[0] = m;
			} else {
				mess = new Message[this.message.length + 1];
				for (int i = 0 ; i<this.message.length ; i++) {
					mess[i] = this.message[i];
				}
				mess[mess.length-1] = m;
			}
			this.message = mess;
		} else {
			System.out.println("Utilisateur du message absent");
		}
	}

	public void afficher() {
		for (int i = 0 ; i<message.length ; i++) {
			if (this.message[i] != null) {
				System.out.println(this.message[i].getUtilisateur().getPseudonyme() + " : " + this.message[i].getMessage());
			}	
		}
		System.out.println("-----------------");
	}

	public void exclusUtilisateur (Utilisateur u) {
		Utilisateur[] uti;
		Message[] mess;
		if (!(estPresent(u))) {
			System.out.println("Utilisateur absent du salon");
		} else {
			uti = new Utilisateur[this.utilisateur.length];
			for (int i = 0 ; i<this.utilisateur.length ; i++) {
				if (this.utilisateur[i] != u) {
					uti[i] = this.utilisateur[i];
				}
			}
			this.utilisateur = uti;
			mess = new Message[this.message.length];
			for (int j = 0 ; j<this.message.length ; j++) {
				for (int k = 0 ; k<this.utilisateur.length ; k++) {
					if (this.message[j].getUtilisateur() == this.utilisateur[k]) {
						mess[j] = this.message[j];
					}
				}
				
			}
			this.message = mess;		
		}
	}
}