public class Noeud {
	private String _etiquette;
	private Noeud _gauche;
	private Noeud _droit;
	private Noeud _arbre;

	public Noeud (String etiquette, Noeud g, Noeud d) {
		this._etiquette = etiquette;
		this._gauche = g;
		this._droit = d;
	}

	public Noeud (String etiquette) {
		this(etiquette, null, null);
	}

	public void afficheInfixe() {
		if (this._gauche != null) {
			this._gauche.afficheInfixe();
		}
		if (this._etiquette != "0") {
			System.out.print(" " + _etiquette);
		}

		if (this._droit != null) {
			this._droit.afficheInfixe();
		}
	}

	public void afficheExpression() {
		if (this._gauche != null) {
			this._gauche.afficheInfixe();
		}
		if (this._etiquette != "0") {
			System.out.print(" " + _etiquette);
			if (isOp(this._etiquette())) {
				System.out.print("(");
			}
		}

		if (this._droit != null) {
			this._droit.afficheInfixe();
		}
	}

/*
	public void affichePrefixe() {
		
		System.out.print(" " + _etiquette);

		if (this._gauche != null) {
			this._gauche.affichePrefixe();
		}

		if (this._droit != null) {
			this._droit.affichePrefixe();
		}
	}

	public void affichePostfixe() {

		if (this._gauche != null) {
			this._gauche.affichePostfixe();
		}

		if (this._droit != null) {
			this._droit.affichePostfixe();
		}
		System.out.print(" " + _etiquette);
	}

	public int nbNoeuds() {
		int count = 1;
		if (this._gauche != null) {
			count += this._gauche.nbNoeuds();
		}
		if (this._droit != null) {
			count += this._droit.nbNoeuds();
		}
		return count;
	}

	public int somme() {
		int count = 0;
		count += this._etiquette;
		
		if (this._gauche != null) {
			count += this._gauche.somme();
		}

		if (this._droit != null) {
			count += this._droit.somme();
		}	
		return count;	
	} */

	public int profondeur() {
		if (this._gauche == null && this._droit == null) {
			return 0;
		}

		if (this._gauche == null) {
			return this._droit.profondeur() + 1;
		}
		if (this._droit == null) {
			return this._gauche.profondeur() + 1;
		}

		if (this._droit.profondeur() > this._gauche.profondeur()) {
			return this._droit.profondeur() + 1;
		} else {
			return this._gauche.profondeur() + 1;
		}

	}
/*
	public boolean recherche(String e) {
		return (this._etiquette == e || (this._gauche != null && (this._gauche._etiquette == e || this._gauche.recherche(e))) || (this._droit != null && (this._droit._etiquette == e || this._gauche.recherche(e))));
	}*/

	public static boolean estNombre (String x) {

		if (!(x.charAt(0) == '+' || x.charAt(0) == '-')) {
			return false;
		}
		for (int i = 1 ; i<x.length() ; i++) {
			if (!(x.charAt(i) >= '0' && x.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}

	public boolean verif() {
		
		if (this._gauche != null) {
			if (isOp(this._etiquette)) {
				return false;
			}
			this._gauche.verif();
		}

		if (this._droit != null) {
			if (isOp(this._etiquette)) {
				return false;
			}
			this._droit.verif();
		}

		if (isOp(this._etiquette)) {
			return false;
		}
		return true;
	}

	public boolean isOp (String s) {
		if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/') {
			return true;
		}
		return false;
	}

/*	public boolean isOpPrio (String s) {
		if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/') {
			return true;
		}
		return false;
	}*/
 
}	