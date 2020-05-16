class Trio {

	Etudiant[] membres;

	Trio (Etudiant e1, Etudiant e2, Etudiant e3) {

		membres = new Etudiant [3];

		membres[0] = e1;
		membres[1] = e2;
		membres[2] = e3;

		System.out.println(java.util.Arrays.toString(membres));

	}

	Etudiant premier() {

		Etudiant meilleur = membres[0];

		for (int i = 0 ; i<membres.length ; i++) {
			if (meilleur.note < this.membres[i].note) {
				meilleur = membres[i];
			}
		}
		return meilleur;
	}

	int classement (String prenom, String nom) {

		Etudiant etu;
		Etudiant [] trie = membres;

		for (int i = 0 ; i<trie.length-1 ; i++) {
			if (trie[i].note < trie[i+1].note) {
				etu = trie[i];
				trie[i] = trie[i+1];
				trie[i+1] = etu;
			}
		}

		for (int j = 0 ; j<trie.length ; j++) {
			if (trie[j].prenom.equals(prenom) && trie[j].nom.equals(nom)) {
				return j+1;
			}
		}

		return 0;
	}

	double moyenne() {

		double moy = 0;
		double x = 0;

		for (int i = 0 ; i<membres.length; i++) {
			moy = moy + membres[i].note;
			x = i;
		}
		
		zzreturn moy/(x+1);
	}
}