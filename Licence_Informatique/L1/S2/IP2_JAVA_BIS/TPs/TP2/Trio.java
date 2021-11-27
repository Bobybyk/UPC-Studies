public class Trio {
	Etudiant[] membres;

	public Trio (Etudiant e1, Etudiant e2, Etudiant e3) {
		this.membres = new Etudiant[3];
		this.membres[0] = e1;
		this.membres[1] = e2;
		this.membres[2] = e3;
	}

	public Etudiant premier() {
		Etudiant p = membres[0];
		for (int i = 1 ; i<this.membres.length ; i++) {
			if (p.getNote() < membres[i].getNote()) {
				p = membres[i];
			} 
		}
		return p;
	}

	public int classement (String prenom, String nom) { //Ne fonctionne pas
		for (int k = 0 ; k<membres.length ; k++) {
			if (membres[k].prenom.equals(prenom) && membres[k].nom.equals(nom)) {
				Etudiant etu = null;
				for (int i = 0 ; i<membres.length ; i++) {
					if (membres[i].equals(prenom) && membres[i].nom.equals(nom)) {
						etu = membres[i];
					}
				}
				int x = 1;
				if (etu != null) {
					for (int j = 0 ; j<membres.length ; j++) {
						if (etu.getNote() < membres[j].getNote()) {
							x++;
						}
					}
				return x;
				}
			}
		}
		return 0;
	}

	public double moyenne() {
		int x = 0;
		for (int i = 0 ; i<membres.length ; i++) {
			x += membres[i].note;
		}
		return x/membres.length;
	}

	public boolean meilleurQueLaMoyenne() {
		if (this.moyenne() > Etudiant.moyenne()) {
			return true;
		} else {
			return false;
		}
	}

}