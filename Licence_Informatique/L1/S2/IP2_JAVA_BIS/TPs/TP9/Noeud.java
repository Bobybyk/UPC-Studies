public class Noeud {
	private int etiquette;
	private Noeud gauche;
	private Noeud droit;

	public Noeud (int etiquette, Noeud g, Noeud d) {
		this.etiquette = etiquette;
		this.gauche = g;
		this.droit = d;
	}

	public Noeud (int etiquette) {
		this(etiquette, null, null);
	}

	public Noeud (Noeud arbre) {
		this.etiquette = arbre.etiquette;
		this.gauche = arbre.gauche;
		this.droit = arbre.droit;
	}

	public Noeud (int[] tab) {
		
	}

	public void afficheInfixe() {
		if (this.gauche != null) {
			this.gauche.afficheInfixe();
		}
		System.out.print(" " + this.etiquette);

		if (this.droit != null) {
			this.droit.afficheInfixe();
		}
	}

	public void affichePrefixe() {
		System.out.print(" " + this.etiquette);
		if (this.gauche != null) {
			this.gauche.affichePrefixe();
		}
		if (this.droit != null) {
			this.droit.affichePrefixe();
		}
	}

	public void affichePostfixe() {
		if (this.gauche != null) {
			this.gauche.affichePostfixe();
		}
		if (this.droit != null) {
			this.droit.affichePostfixe();
		}
		System.out.print(" " + this.etiquette);
	}

	public int nbDeNoeuds (int x) {
		if (this.gauche != null) {
			return this.gauche.nbDeNoeuds(x+1);
		}
		if (this.droit != null) {
			return this.droit.nbDeNoeuds(x+1);
		}
		return x + 1; // +1 pour prendre en compte le premier noeuds
	}

	public int somme () {
		int count = 0;
		count += this.etiquette;
		
		if (this.gauche != null) {
			count += this.gauche.somme();
		}

		if (this.droit != null) {
			count += this.droit.somme();
		}	
		return count;	
	}

	public int profondeur() {
		int nbGG = this.gauche == null ? 0:this.gauche.profondeur();
		int nbGD = this.droit == null ? 0:this.droit.profondeur();

		return nbGG > nbGD ? nbGG+1:nbGD+1;
	}

	public boolean recherche(int e) {

		return (this.etiquette == e || (this.gauche != null && (this.gauche.etiquette == e || this.gauche.recherche(e))) || (this.droit != null && (this.droit.etiquette == e || this.gauche.recherche(e))));
	
	}
}