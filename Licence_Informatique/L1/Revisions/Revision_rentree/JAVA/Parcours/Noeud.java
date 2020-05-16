public class Noeud {
	private int _etiquette;
	private Noeud _gauche;
	private Noeud _droite;
	private final int _vol;
	//private int[] _arbre;

	public Noeud (int e, Noeud g, Noeud d, int v) {
		this._etiquette = e;
		this._gauche = g;
		this._droite = d;
		this._vol = v;
		//this._arbre = new int[longueur(1)+1];
	}

	public Noeud (int e, int v) {
		this._etiquette = e;
		this._gauche = null;
		this._droite = null;
		this._vol = v;
		//this._arbre = new int[longueur(1)+1];
	}

/*	public void remplir (int x) {

		//System.out.print(" " + _etiquette);
		this._arbre[x] = this._etiquette;
		x++;
		if (this._gauche != null) {
			this._gauche.remplir(x);
		}
		if (this._droite != null) {
			this._droite.remplir(x);
		}
	}*/

	public int longueur () {
		int x = this.longueurBis(0);
		return x;
	}

	public int longueurBis (int x) {
		x++;
		if (this._gauche != null) {
			this._gauche.longueurBis(x);
		}
		if (this._droite != null) {
			this._droite.longueurBis(x);
		}
		return x;		
	}

/*	public void graph() {
		int prof = profondeur();
		int marge = prof * 3;
		for (int i = 0 ; i<marge ; i++) {
			System.out.print(" ");
		}
		System.out.print("x");
		for (int i = 0 ; i<marge ; i++) {
			System.out.print(" ");
		}
		System.out.println("");

	}	*/

/*	public void afficher() {
		for (int i = 0 ; i<this._arbre.length ; i++) {
			System.out.print(this._arbre[i] + ".");
		}
	}*/

/*
	                             9                  
                /                                   \
               2                                     7       
       /               \                     /               \  
      9                 9                   1                 8
   /     \           /     \             /     \           /     \ 
  7       7         7       7           7       7         7       7
 / \     / \       / \     / \         / \     / \       / \     / \
6   6   6   6     6   6   6   6       6   6   6   6     6   6   6   6

      */


	public int profondeur() {
		int nbGG = this._gauche == null ? 0:this._gauche.profondeur();
		int nbGD = this._droite == null ? 0:this._droite.profondeur();

		return nbGG > nbGD ? nbGG+1:nbGD+1;
	}

/*	public void descente() {
		if (this._etiquette > 0) {
			if ((this._gauche != null) || (this._etiquette > 0)) {
				this._etiquette--;
				this._gauche._etiquette++;
				this.parcours(0);
				System.out.println("");
				this.descente();
			} 
			else if (this._gauche != null) {
				this._gauche.descente();
			}

			if ((this._droite != null) || (this._droite._vol < this._droite._etiquette)) {
				this._etiquette--;
				this._droite._etiquette++;
				this.parcours(0);
				System.out.println("");
				this.descente();
			}
		}
	}*/

}