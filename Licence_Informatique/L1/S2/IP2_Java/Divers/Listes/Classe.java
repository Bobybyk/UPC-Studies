public class Classe {
	public Eleve teteDeClasse;

	public Classe () {
		this.teteDeClasse = null;
	}
	
	public void prendreTete (Objets o) {
		
		Eleve nvxTete = new Eleve(o);

		if (this.teteDeClasse == null) {
			this.teteDeClasse = nvxTete;
		} 
		else if (o.meilleur() > o.getEleve().getNote()) {			// A revoir
			Eleve ancienMeilleur = this.teteDeClasse;
			
			this.teteDeClasse = nvxTete;
			nvxTete.setSuivant(ancienMeilleur);
		}
	}

	public void ajouterEleve (Objets o) {

		Eleve index = this.teteDeClasse;

		while(index.getSuivant() != null) {
			index = index.getSuivant();
		}
		Eleve ele = new Eleve(o);
		index.setSuivant(ele);
	}

	public void affichage () {

		if (teteDeClasse == null) {
			System.out.println("Cette classe est vide");
		}

		else {
			Eleve index = this.teteDeClasse;
			
			while (index != null) {
				System.out.println(index.getObjet().getNom() + " : " + index.getObjet().getNote() + "/20");
				index = index.getSuivant();
			}
		}
	}

}