public class ListeDEntiers {
	public Cellule premier;
	public Cellule dernier;

	public ListeDEntiers() {
		this.premier = null;
		this.dernier = null;
	}

	public void copier() {
		
	}

	public void ajouteFin(int x) {
		Cellule index = this.premier;
		Cellule c = new Cellule(x);

		if (this.premier == null) {
			this.premier = c;
			this.dernier = c;
		} else {
			while(index.getSuivant() != null) {
				index = index.getSuivant();
			}
			index.setSuivant(c);
			index.getSuivant().setPrecedent(index);
			this.dernier = index;
		}
	}

	public void ajouteDebut(int x) {
		Cellule index = this.dernier;
		Cellule c = new Cellule(x);

		if (this.dernier == null) {
			this.premier = c;
			this.dernier = c;
		} else {
			while (index.getPrecedent() != null) {
				index = index.getPrecedent();
			}
			index.setPrecedent(c);
			index.getPrecedent().setSuivant(index);
			this.premier = index;
		}
	}

	/*public void affiche() {
		Cellule index = this.premier;
		if (this.premier == null) {
			System.out.println("La liste est vide");
		} else {
			while(index.getSuivant() != null) {
				System.out.println(index.getValeur());
				index = index.getSuivant();
			}
		}
	}*/

	public void affiche() {
		if (this.premier == null) {
			System.out.println("La liste est vide");
		} else {
			this.premier.affiche();
		}
	}

	public void additionne() {
		if (this.premier != null) {
			this.premier.additionne();	
		}
	}

	public void afficheInverse() {
		Cellule index = this.dernier;
		if (this.dernier == null) {
			System.out.println("La liste est vide");
		} else {
			while(index.getPrecedent() != null) {
				System.out.println(index.getValeur());
				index = index.getPrecedent();
			}
			System.out.println(index.getValeur());
		}
	}

}