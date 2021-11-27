import java.util.LinkedList;

public class Dossier extends Element implements Affichable {
	LinkedList<Entree> entree;
	private Dossier parent;

	public Dossier () {
		this.parent = null;
	} 

	public Entree getEntree(String nom, boolean creer) {
		if (this.entree != null) {
			for (int i = 0 ; i<this.entree.size() ; i++) {
				if (this.entree.get(i).getNom().equals(nom)) {
					return this.entree.get(i);
				}
			}
		}
		if (creer) {
			Entree e = new Entree (this.parent, nom);
			return e;
		}
		return null;
	}

	public void afficher() {
		if (this.entree != null) {
			for (int i = 0 ; i<this.entree.size() ; i++) {
				this.entree.get(i).getNom();
			}
		}
	}

	public String getType() {
		return "dossier";
	}

	public LinkedList<Entree> getListe() {
		return this.entree;
	}

	public Dossier getParent() {
		return this.parent;
	}

}