public class Noeud {
	private int _etiquette;
	private Noeud _gauche;
	private Noeud _droit;
	private Noeud _arbre;

	public Noeud (int etiquette, Noeud g, Noeud d) {
		this._etiquette = etiquette;
		this._gauche = g;
		this._droit = d;
	}

	public Noeud (int etiquette, int profondeur) {
		this(etiquette, null, null);
	}

	public static Noeud test() {
		Noeud[] n = new Noeud[10];
		n[4] = new Noeud(4);
		n[1] = new Noeud(1, null, n[4]);
		n[0] = new Noeud(0, 2);
		n[7] = new Noeud(7, n[1], n[0]);
		n[8] = new Noeud(8);
		n[6] = new Noeud(6, n[8], null);
		n[2] = new Noeud(2);
		n[9] = new Noeud(9, n[6], n[2]);
		n[5] = new Noeud(5, null, n[9]);
		n[3] = new Noeud(3, n[7], n[5]);

		return n[3];
	}

	public void espace (int n) {
		if (n > 0) {
			System.out.print(" ");
			espace(n-1);
		}
	}

	public void affiche (int p) {
		if (this._gauche != null) {
			if (this._gauche._profondeur == p) {
				System.out.println(_etiquette + 1);
			} else {
				this._gauche.affiche(p);
			}
		}

		if (this._droit != null) {
			if (this._droit._profondeur == p) {
				System.out.println(_etiquette + 1);
			} else {
				this._droit.affiche(p);
			}
		}
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