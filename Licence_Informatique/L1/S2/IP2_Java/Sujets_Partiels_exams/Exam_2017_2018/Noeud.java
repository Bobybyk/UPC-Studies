public class Noeud {
	private boolean etiquette;
	private Noeud gauche;
	private Noeud droit;

	public Noeud (boolean etiquette) {
		this.etiquette = etiquette;
		this.gauche = null;
		this.droit = null;
	} 

	public Noeud (boolean etiquette, Noeud gauche, Noeud droit) {
		this.etiquette = etiquette;
		this.gauche = gauche;
		this.droit = droit;
	}

	public void affichage () {
		System.out.println(etiquette);
		if (this.gauche != null) {
			this.gauche.affichage();
		}
		if (this.droit != null) {
			this.droit.affichage();
		}
	}

	public int nbCheminsPoids (int x) {
		if (this.gauche != null) {
			x++;
			this.gauche.nbCheminsPoids(x);
		}
		if (this.droit != null) {
			x++;
			this.droit.nbCheminsPoids(x);
		}
	}
}