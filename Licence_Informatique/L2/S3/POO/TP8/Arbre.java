public class Arbre {
	Noeud racine;

	public Arbre(Noeud n) {
		this.racine = n;
	}

	public void map(StringTransformation t) {
  		this.racine.fils.forEach(f->f.map(t));
	}

	public void traverser (String extension) {
		this.racine.fils.forEach(f->f.traverser(extension));
	}

	public void supprimer (String extension) {
		
	}

	public void afficher() {
		this.racine.afficher();
	}
}