public class CellRob {
	private CellRob suivante;
	private CellRob precedente;
	private Robot rob;

	public CellRob (Robot rob, CellRob suivante, CellRob precedente) {
		this.precedente = precedente; //(precedente == null) ? this : precedente;
		this.suivante = suivante; //(suivante == null) ? this : suivante;
		this.rob = rob;
	}

	public void affiche() {
		System.out.println(this.rob.description());
	}

	public void supprimer (int id) {
		CellRob tmp = this;
		do {
			if (tmp.rob.getId() == id) {
				tmp.precedente.suivante = tmp.suivante;
				tmp.suivante.precedente = tmp.precedente;
			}
			tmp = tmp.suivante;
		}
		while (tmp.suivante != this);
	}

	public CellRob getPrecedente() {
		return this.precedente;
	}
	public CellRob getSuivante() {
		return this.suivante;
	}
	public Robot getRob() {
		return this.rob;
	}

	public void setPrecedente (CellRob p) {
		this.precedente = p;
	}
	public void setSuivante (CellRob s) {
		this.suivante = s;
	}
}