public class Element {
	private int valeur;
	private Element suivant;
	private Element precedent;

	public Element (int valeur, Element suivant, Element precedent) {
		this.valeur = valeur;
		this.suivant = suivant;
		this.precedent = precedent;
	}

	public int longueur() {
		if (suivant != null) {
			return this.suivant.longueur() + 1; //Si le suviant n'est pas null, on return les éléments parcourus + le suivant
		} else {
			return 1; //Si le suivant est null, on return le courant (qui s'additionnera aux autres éléments)
		}
	}

	public void affiche() {
		if (suivant != null) {
			System.out.print(valeur + " ");
			suivant.affiche();
		} else {
			System.out.print(valeur + " ");
		}
	}

	public void afficheInverse() {
		if (this.precedent != null) {
			System.out.println(valeur + " ");
			precedent.afficheInverse();
		} else {
			System.out.println(valeur + " ");
		}
	}

	public void ajouteDebut (int valeur) {

	}

	public void ajouteAvant (Element e, int v) {
		if (suivant == null && this != e) {
			System.out.println("Element absent");
		}
		else if (this == e) {
			Element tmp = new Element(v);
			precedent.suivant = tmp;
			precedent = tmp;
		} else {
			this.suivant.ajouteAvant(e, v);
		}
	}
}