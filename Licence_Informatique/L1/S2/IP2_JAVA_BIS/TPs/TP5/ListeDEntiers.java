public class ListeDEntiers {
	private Cellule premier;

	public ListeDEntiers () {
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

	public int taille() {
		if (this.premier == null) {
			return 0;
		} else {
			return this.premier.taille();	
		}
	}

	public int somme () {
		if(this.premier != null){
			return this.premier.somme();
		}else{
			return 0;
		}
	}

	public void ajouter_en_i (int i, int v) {
 		Cellule  index = this.premier;
 		if (index != null) {
 			if ((this.taille() - 1) < i) {
 				i = this.taille() - 1; 
 			}
 			index.ajouter_en_i(i, v);
 		}
	}

	public void supprimer_en_i (int i) {
		
		if (i == 0) {
			this.premier = this.premier.getSuivante();
		}
		else if (i > this.taille()) {
			i = this.taille() -1;
			this.premier.supprimer_en_i(i);
		} else {
			this.premier.supprimer_en_i(i);
		}
	}

	public int supprimer_k_premieres_occ (int k, int v) {

		if (this.taille() == 0) {
			return 0;
		}
		else {
			return this.premier.supprimer_k_premieres_occ(k, v);
		}
	}

 	public boolean egal (ListeDEntiers l) {
 		if ((l.description()).equals(this.description())) {
 			return true;
 		} else {
 			return false;
 		}
 	}

	public String description () {

		if (this.premier == null) {
			return "La liste est vide.";
		} else {
			return "(" + this.premier.description();
		}
	}
}