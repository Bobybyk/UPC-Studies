public class Cellule {
	private Cellule precedente;
	private Cellule suivante;
	private boolean noire;
	private boolean prochainEtape;

	public Cellule (boolean noire) {
		this.noire = noire;
		this.precedente = null;
		this.suivante = null;
		this.prochainEtape = false;
	}

	public void prochaineEtape() {
		
		if ((this.suivante != null) && (this.precedente == null) && (this != null)) {
			if ((this.noire == false) && (this.suivante.noire == false)) {
				this.prochainEtape = false;
			} else {
				this.prochainEtape = true; 
			}
		}
		else if ((this.suivante == null) && (this.precedente != null) && (this != null)) {
			if ((this.noire == false) && (this.precedente.noire == false)) {
				this.prochainEtape = false;
			} else {
				this.prochainEtape = true;
			}
		}
		else if ((this.precedente != null) && (this.suivante != null) && (this != null)) {
			if ((this.noire == this.precedente.noire) && (this.noire == this.suivante.noire)) {
				this.prochainEtape = false;
			} else {
				this.prochainEtape = true;
			}
		}
		else if ((this.precedente == null) && (this.suivante == null) && (this != null)) {
			if (this.noire = false) {
				this.prochainEtape = false;
			} else {
				this.prochainEtape = true;
			}
		}
	}

	public void affiche() {
		if (this.noire == true) {
			System.out.print("#");
		} else {
			System.out.print("-");
		}
	}

	public Cellule getPrecedente(){
		return this.precedente;
	}
	public Cellule getSuivante() {
		return this.suivante;
	}
	public boolean getNoire() {
		return this.noire;
	}
	public boolean getProchain() {
		return this.prochainEtape;
	}

	public void setPrecedente(Cellule p) {
		this.precedente = p;
	}
	public void setSuivante (Cellule s) {
		this.suivante = s;
	}
	public void setProchain (boolean b) {
		this.prochainEtape = b;
	}
	public void setNoire (boolean b) {
		this.noire = b;
	}

}