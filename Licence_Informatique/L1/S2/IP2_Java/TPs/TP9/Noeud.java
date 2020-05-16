public class Noeud {
	private int _etiquette;
	private Noeud _gauche;
	private Noeud _droit;

	public Noeud (int etiquette, Noeud g, Noeud d) {
		this._etiquette = etiquette;
		this._gauche = g;
		this._droit = d;
	}

	public Noeud (int etiquette) {
		this(etiquette, null, null);
	}

	public void afficheInfixe() {
		if (this._gauche != null) {
			this._gauche.afficheInfixe();
		}
		System.out.print(" " + _etiquette);

		if (this._droit != null) {
			this._droit.afficheInfixe();
		}
	}

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
	} 

	public int profondeur() {

		int nbGG = this._gauche == null ? 0:this._gauche.profondeur();
		int nbGD = this._droit == null ? 0:this._droit.profondeur();

		return nbGG > nbGD ? nbGG+1:nbGD+1;

	}

	public boolean recherche(int e) {
		
		return (this._etiquette == e || (this._gauche != null && (this._gauche._etiquette == e || this._gauche.recherche(e))) || (this._droit != null && (this._droit._etiquette == e || this._gauche.recherche(e))));
	
	}
}