public class Entree {
	private Element element;
	private String nom;
	private Dossier parent;

	public Entree (Dossier p, String n) {
		this.element = null;
		this.nom = n;
		this.parent = p;
	} 

	public String toString () {
		String r = "";
		if (this.element.getType().equals("texte")) {
			r = "texte";
		}
		if (this.element.getType().equals("dossier")) {
			r = "dossier";
		} else { r = "entrée vide"; }

		return "nom " + r;
	}

	public void supprimer() {
		this.parent = null;
	}

	public void remplacer(Element e) {
		this.element = e;
	}

	public String getNom() {
		return this.nom;
	}

	public Element getElement() {
		return this.element;
	}

}