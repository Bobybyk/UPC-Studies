public class Liste {
	private Element premier;
	private Element dernier;

	public Liste() {
		this.premier = null;
		this.dernier = null;
	}

	public int longueur() {
		if (premier == null) {
			return 0;
		} else {
			return premier.longueur();
		}
	}

	public void affiche() {
		if (premier == null) {
			System.out.println("La liste est vide");
		} else {
			this.premier.affiche();
		}
	}

	public void afficheInverse() {
		if (precedent == null) {
			System.out.println("La liste est vide");
		} else {
			this.dernier.afficheInverse();
		}
	}

	public void ajouteDebut (int valeur) {
		if (this.premier == null) {

		}
	}

	public void ajouteAvant (Element e, int v) {
		if (premier == null) {
			System.out.println("Liste vide");
		} else {
			this.premier.ajouteAvant(e, v);
		}
	}
}