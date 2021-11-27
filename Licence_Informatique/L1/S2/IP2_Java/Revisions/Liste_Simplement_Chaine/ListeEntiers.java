public class ListeEntiers {
	private Cellule premier;

	public ListeEntiers() {
		this.premier = null;
	}

	public void add (int x) {
		Cellule index = this.premier;
		Cellule c = new Cellule(x);
		if (this.premier != null) {
			while (index.getSuivante() != null) {
				index = index.getSuivante();
			}
			index.setSuivante(c);
		}
		else if (index == null) {
			this.premier = c;
		}
	}

	public void additionner() {
		Cellule index = this.premier;
		if (this.premier != null) {
			while (index.getSuivante() != null) {
				index.setVal(index.getVal() + index.getSuivante().getVal());
				index = index.getSuivante();
			}
		}
	}

	public int longueur() {
		Cellule index = this.premier;
		int x = 0;
		if (this.premier != null) {
			while (index != null) {
				index = index.getSuivante();
				x++;
			}
		}
		return x;
	}

	public void afficher() {
		Cellule index = this.premier;
		if (this.premier != null) {
			while (index != null) {
				index.afficher();
				index = index.getSuivante();
			}
		}
		System.out.println("");
	}
}