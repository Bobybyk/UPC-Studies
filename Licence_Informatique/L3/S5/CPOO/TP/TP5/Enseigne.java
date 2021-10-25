import java.awt.Color;

public enum Enseigne {
	PIQUE(Color.BLACK), COEUR(Color.RED), CARREAU(Color.RED), TREFLE(Color.BLACK);
	public final Color couleur;

	private Enseigne (Color c) {
		this.couleur = c;
	}
	public Color getCouleur() {
		return couleur;
	}

}