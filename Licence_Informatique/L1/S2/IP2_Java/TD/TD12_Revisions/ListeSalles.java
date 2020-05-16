public class ListeSalles {
	private CSalle premiere; 


	public void estVide() {
		return this.premiere == null;
	}

	public int numDansCouloir (int idSalle) {
		CSalle tmp = this.premiere;
		int res = 1;
		while (tmp != null && tmp.getS().getId() != idSalle) {
			tmp = tmp.getSuivant();
			res++;
		}
		return (tmp == null ? -1 : res);
	}

	public Salle salleAvecVideo() {
		CSalle test = this.premiere;
		while(test != null && !(test.getS().getVideo())) {
			test = test.getSuivant();
		}
		if (test == null) {
			return null;
		} else {
			return test.getS();
		}
	}

	public Salle salleAvecVideo (int capMin) {
		Salle tmp = this.premiere;
		while (tmp suivante != null) {
			if (tmp.suivante != null) {
				if (tmp.getS().getVideo() && tmp.getS().getCapacite() >= capMin) {
					return tmp.getS();
				} else {
					tmp = tmp.getSuivant();
				}
			}
		} return null;
	}

	public int coupeSalles (int cap) {
		CSalle tmp = this.premiere;
		int res = 0;

		while (tmp != null) {
			Salle s = tmp.getS();
			if (s.getCapacite() >= cap) {
				int k = s.getCapacite()/2;
				s.setCapacite(k);
				Salle ajout = new Salle(k, s.getVideo());
				CSalle cAjout = new CSalle (ajout, s.getSuivant());
				s.setSuivante(cAjout);
				res++;
			}
			tmp = tmp.getSuivant();
		} 
		return res;
	}

	
}