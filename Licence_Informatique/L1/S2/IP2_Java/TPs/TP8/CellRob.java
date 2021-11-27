public class CellRob {
	private Robot robot;
	private CellRob suivante;
	private CellRob precedente;

	public CellRob (Robot rob, CellRob suiv, CellRob prec) {
		this.robot = rob;
		this.suivante = suiv;
		this.precedente = prec;
	}

	public void supprimer (int id) {
		CellRob tmp = this;
		do {
			if (tmp.robot.getId() == id) {
				tmp.precedente.suivante = tmp.suivante;
				tmp.suivante.precedente = tmp.precedente;
			}
			tmp = tmp.suivante;
		}
		while (tmp.suivante != this);
	}

	public void supprimer (char nom) {
		CellRob tmp = this;
		do {
			if (tmp.robot.getNom() == nom) {
				tmp.precedente.suivante = tmp.suivante;
				tmp.suivante.precedente = tmp.precedente;
			}
			tmp = tmp.suivante;
		}
		while (tmp.suivante != this);
	}

	public void affiche() {
		this.robot.description();
	}

	public Robot getRobot() {
		return this.robot;
	}
	public CellRob getSuivante() {
		return this.suivante;
	} 
	public CellRob getPrecedente() {
		return this.precedente;
	}

	public void setSuivante(CellRob s) {
		this.suivante = s;
	}
	public void setPrecedente(CellRob p) {
		this.precedente = p;
	}
}