public class Noeud2 {
	private int etiquette;
	private Noeud2 gauche;
	private Noeud2 droit;


	public Noeud2 (int e) {
		this.etiquette = e;
		this.gauche = null;
		this.droit = null;
	}
	public Noeud2 (int e, Noeud2 g, Noeud2 d) {
		this.etiquette = e;
		this.gauche = g;
		this.droit = d;
	}
}