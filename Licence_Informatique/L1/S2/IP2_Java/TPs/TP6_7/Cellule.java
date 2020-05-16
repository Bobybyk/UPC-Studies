public class Cellule {
	private Cellule precedente;
	private Cellule suivante;
	private boolean noire;
	private boolean prochainEtat;

		public Cellule (boolean noire) {
			this.precedente = null;
			this.suivante = null;
			this.noire = noire;
			this.prochainEtat = false;
		}

		public Cellule getPrecedente() {
			return this.precedente;
		}
		public Cellule getSuivante() {
			return this.suivante;
		}
		public boolean getNoire() {
			return this.noire;
		}
		public boolean getProchainEtat() {
			return this.prochainEtat;
		}

		public void setPrecedente (Cellule p) {
			this.precedente = p;
		}
		public void setSuivante (Cellule s) {
			this.suivante = s;
		}
		public void setNoire (boolean n) {
			this.noire = n;
		}
		public void setProchainEtat (boolean pE) {
			this.prochainEtat = pE;
		}

		public void prochaineEtape() {
			
			

				if ((this.suivante != null) && (this.precedente == null) && (this != null)) {
					if ((this.noire == false) && (this.suivante.noire == false)) {
						this.prochainEtat = false;
					} else {
						this.prochainEtat = true; 
					}
				}
				else if ((this.suivante == null) && (this.precedente != null) && (this != null)) {
					if ((this.noire == false) && (this.precedente.noire == false)) {
						this.prochainEtat = false;
					} else {
						this.prochainEtat = true;
					}
				}
				else if ((this.precedente != null) && (this.suivante != null) && (this != null)) {
					if ((this.noire == this.precedente.noire) && (this.noire == this.suivante.noire)) {
						this.prochainEtat = false;
					} else {
						this.prochainEtat = true;
					}
				}
				else if ((this.precedente == null) && (this.suivante == null) && (this != null)) {
					if (this.noire = false) {
						this.prochainEtat = false;
					} else {
						this.prochainEtat = true;
					}
				}
			}

		public void afficher() {
			if (this.noire == true) {
				System.out.print("#");
			} else {
				System.out.print("-");
			}
		} 

}