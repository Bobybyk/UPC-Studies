public class TableRonde {
	private CellRob courant;

	public TableRonde (Robot rob) {
		this.courant = new CellRob(rob, null, null);
		this.courant.setPrecedente(this.courant);
		this.courant.setSuivante(this.courant);
	}

	public void affiche() {
		CellRob index = this.courant;
		if (this.courant == null) {
			System.out.println("La table est vide");
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
				if (this.courant.getRob().getId() == id) {
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

	public void discussion() {
		CellRob index = this.courant;
		while (this.courant != null) {
			index.getRob().parle(5);
			if (index.getRob().finiDeParler()) {
				supprimer(index.getRob().getId());
			}
			index = index.getSuivante();
		}
	}
}