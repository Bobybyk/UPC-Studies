public class Groupe {
	public Cellule chefDeFile;

 
	public Groupe () {
		this.chefDeFile = null;
	}

	public void prendreTete (Robot r) {
		
		if (r.nomCorrect()) {

			Cellule nvxChef = new Cellule(r);

			if (this.chefDeFile == null) {
				this.chefDeFile = nvxChef;

			} else {
				Cellule ancienChef = this.chefDeFile;

				this.chefDeFile = nvxChef;
				nvxChef.setSuivant(ancienChef);
			}
		}
	}

	public void ajouteNouveau (Robot r) {
		
		if (r.nomCorrect()) {

			Cellule index = this.chefDeFile;

			while(index.getSuivant() != null) {
				index = index.getSuivant();
			}
			Cellule cel = new Cellule(r);
			index.setSuivant(cel);
		}
	}

	public void affiche () {
		
		if (this.chefDeFile == null) {
			System.out.println("La liste est vide");
		} else {
			Cellule index = this.chefDeFile;

			while(index != null) {
				System.out.println(index.getRob().getTexte());
				index = index.getSuivant();
			}
		}
	}

	public int numerologie () {

		Cellule index = this.chefDeFile;
		int val = 0;

		while(index != null) {
			val += ((index.getRob().getNom())-'a') + 1;
			index = index.getSuivant();
		}
		return val%9;
	}

	public String bandName () {
		String nomGroupe = "";
		Cellule index = this.chefDeFile;
		while (index != null) {
			nomGroupe = nomGroupe + (index.getRob().getNom());
			index = index.getSuivant();
		}
		return nomGroupe;
	}

	public void chantez () {

		Cellule index = this.chefDeFile;

		while(index != null) {
			index.getRob().chante();
			index = index.getSuivant();
		}
	}
 
	public Groupe couperAPartirDe (char nom) {

		Cellule index = this.chefDeFile;
		Groupe e = new Groupe();

		while(index.getRob().getNom() != nom && index != null) {
			index = index.getSuivant();
		}

		if (index.getRob().getNom() == nom) {
			if (e != null) {
				e.prendreTete(index.getRob());
				index.setSuivant(null);
			} else {
				e.ajouteNouveau(index.getRob());
				index.setSuivant(null);
			}
		}
		return e;
	}

}