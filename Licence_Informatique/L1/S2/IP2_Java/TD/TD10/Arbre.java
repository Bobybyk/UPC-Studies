public class Arbre {
	private Noeud _racine;

	public Arbre() {
		this._racine = null;
	}

	public Arbre (Noeud n) {
		this._racine = n;
	}

	public void bourgeons() {
		if (this._racine != null) {
			this._racine.bourgeons();
		}
	}

	public void elagage() {
		if (this._racine != null) {
			if ((this._racine.getDroit() != null) && this._racine.getGauche() != null) {
				this._racine.elagage();
			} else {
				this._racine = null;
			}
		}
	}

	public int bourgeons() {
		int nbNoeud = this._racine.nbNoeud();
		if (this._racine != null) {
			this._racine.bourgeons();
		}
		return this._racine.nbNoeud - nbNoeud;
	}

	public int elagage() {
		int nbNoeud = this._racine.nbNoeud();
		if (_racine != null) {
			if (_racine.getGauche() == null && _racine.getDroit == null) {
				racine = null;
			}
		}
		return nbNoeud-this.racine.nbNoeud();
	}

	public int decroissance() {
		int nbNoeud = this._racine.nbNoeud();
		if (this._racine != null) {
			this._racine.decroissance();
		}
		return nbNoeud - this._racine.nbNoeud();
	}

	public int croissance() {
		int nbNoeud = this.racine.nbNoeud();
		if (this._racine != null) {
			this._racine.croissance();
		}
		return this._racine.nbNoeud - nbNoeud;
	}

	public Arbre sousArbre (String chemin) {
		if (racine != null) {
			return new Arbre(racine.sousArbre(chemin));
		} else {
			return null;
		}
	}
}