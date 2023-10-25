public class TableRonde {
	private CellRob courant;

	public TableRonde (Robot r) {

		this.courant = new CellRob (r, null, null);
		this.courant.setPrecedente(this.courant);
		this.courant.setSuivante(this.courant);
	}	

	public void ajouteRob(Robot r) {
		if (this.courant == null) {
			this.courant = new CellRob(r, null, null);
			this.courant.setPrecedente(this.courant);
			this.courant.setSuivante(this.courant);
		} else {
			CellRob newRob = new CellRob(r, this.courant, this.courant.getPrecedente());
			this.courant.getPrecedente().setSuivante(newRob);
			this.courant.setPrecedente(newRob);
		}
	}

	public boolean supprimer (int id) {
		if (this.courant != null) {
			if (this.courant.getSuivante() == this.courant) {
				if (this.courant.getRobot().getId() == id) {
					this.courant = null;
					return true;
				} else {
					return false;
				}
			} else {
				this.courant.supprimer(id);
				return true;
			} 
		}
		return false;
	}

	public boolean supprimer (char nom) {
		if (this.courant != null) {
			if (this.courant.getSuivante() == this.courant) {
				if (this.courant.getRobot().getNom() == nom) {
					this.courant = null;
					return true;
				} else {
					return false;
				}
			} else {
				this.courant.supprimer(nom);
				return true;
			} 
		}
		return false;
	}

/*	public void parle () {
		CellRob index = this.courant.getSuivante();
		while (index.getRobot().getNbRob() ) {
		if (this.courant == null) {
			return;
		} else {
			index.getRobot().parle(5);
			if (index.getRobot().finiDeParler()) {
				supprimer(index.getRobot().getNom());
				index.getRobot().setNbRob();
			}
			index = index.getSuivante();
			if (index != null) {
				while (index != this.courant) {
					index.getRobot().parle(5);
					if (index.getRobot().finiDeParler()) {
						supprimer(index.getRobot().getNom());
						index.getRobot().setNbRob();
					}
					index = index.getSuivante();				
				}
			}
		}
		}
	}*/

	public void affiche() {
		CellRob index = this.courant.getSuivante();
		if (this.courant == null) {
			return;
		} else {
			index.affiche();
			index = index.getSuivante();
			if (index != null) {
				while (index != this.courant) {
					index.affiche();
					index = index.getSuivante();
				}
			}
		}
	}
	
}