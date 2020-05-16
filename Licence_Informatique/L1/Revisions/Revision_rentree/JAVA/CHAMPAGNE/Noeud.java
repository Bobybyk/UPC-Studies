public class Noeud {
	private Noeud _gauche;
	private Noeud _droite;
	private Verre _verre;

	public Noeud (Noeud g, Noeud d, Verre v) {
		this._gauche = g;
		this._droite = d;
		this._verre = v;
	}

	public Noeud (Verre v) {
		this._gauche = null;
		this._droite = null;
		this._verre = v;
	}

	
}