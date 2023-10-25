public class Noeud {
	private Noeud _gauche;
	private Noeud _droit;

	public Noeud (Noeud g, Noeud d) {
		this._gauche = g;
		this._droit = d;
	}

	public void bourgeons() {
		if (this._droit != null) {
			this._droit.bourgeons();
		}
		if (this._gauche != null) {
			this._gauche.bourgeons();
		}
		if ((this._droit == null) && (this._gauche == null)) {
			this._droit = new Noeud(null, null);
			this._gauche = new Noeud(null, null);
		}
	}

	public void elagage() {
		if (this._gauche != null) {
			if ((this._gauche._gauche == null) && (this._gauche._droit == null)) {
				this._gauche = null;
			} else {
				this._gauche.elagage();			
			}
		}
	}

	public void croissance() {
		if (this._gauche != null) {
			Noeud tmp = this._gauche;
			this._gauche = new Noeud(tmp, null) ;
			tmp.croissance();
		}
		if (this._droit != null) {
			Noeud tmp = this._droit;
			this._droit = new Noeud(null, tmp);
			tmp.croissance();
		}  
	}

	public void decroissance() {
		if (this._gauche != null) {
			if (this._gauche._gauche != null) {
				this._gauche = this._gauche._gauche;
			}
			this._gauche.decroissance();
		}
		if (this._droit != null && this._droit._droit != null) {
			this._droit.decroissance();
		}
	}

	public int nbNoeuds() {
		return 1 + (this._gauche != null ? this._gauche.nbNoeuds():0+this._droit != null?this._droit.nbNoeuds():0);
	}

	public Noeud sousArbre (String chemin) {
		Noeud tmp = this;
		for (int i = 0 ; i<length() ; i++) {
			if (chemin.charAt(i) == 'g') {
				tmp = tmp._gauche;
			}
			if (chemin.charAt(i) == 'd') {
				tmp = tmp._droit;
			}
			if (tmp == null) {
				return null;
			}
		}
		return tmp;
	}
}