public class Noeud {
	private int etiquette;
	private Noeud gauche;
	private Noeud droit;

	public Noeud (int e) {
		this(e, null, null);
	}

	public Noeud (int e, Noeud g, Noeud d) {
		this.etiquette = e;
		this.gauche = g;
		this.droit = d;
	}

	public void afficheInfixe() {
		if (this.gauche != null) {
			this.gauche.afficheInfixe();
		}
		System.out.print(etiquette);
		if (this.droit != null) {
			this.droit.afficheInfixe();
		}
	}

	public void affichePostfixe() {
		if (this.gauche != null) {
			this.gauche.affichePostfixe();
		}
		if (this.droit != null) {
			this.droit.affichePostfixe();
		}
		System.out.print(etiquette);
	}

	public void affichePrefixe() {
		System.out.print(etiquette);
		if (this.gauche != null) {
			this.gauche.affichePrefixe();
		}
		if (this.droit != null) {
			this.droit.affichePrefixe();
		}
	}
}