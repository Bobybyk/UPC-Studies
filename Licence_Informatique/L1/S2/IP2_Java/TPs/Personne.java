public class Personne {
	private final String _prenom, _nomDeFamille;
	private Personne _mere, _pere;

	public Personne (String prenom, String nomDeFamille) {
		this._prenom = prenom;
		this._nomDeFamille = nomDeFamille;
	}

	public Personne (String prenom, String nomDeFamille, Personne pere, Personne mere) {
		this._prenom = prenom;
		this._nomDeFamille = nomDeFamille;
		this._pere = pere;
		this._mere = mere;
	}

	public boolean estFrereOuSoeur (Personne p) {

		return (this._pere != null && this._pere == p.pere) && (this._mere != null && this._mere = p.pere);

	}

	public boolean estCousinGermain (Personne p) {
		return (this._pere != null && ((p.pere != null && this._pere.estFrereOuSoeur(p.pere) || (p.mere != null && this._pere.estFrereOuSoeur(p._mere)))) || (this._mere != null && ((p.pere != null && this._mere.estFrereOuSoeur(p.pere)) || (p.mere != null && this._mere.estFrereOuSoeur(p.mere)))));
	}

	public int nbAscendantsVivants() {
		int count = 1;
		if (this._pere != null) {
			count += this._pere.nbAscendantsVivants();
		}
		if (this._mere != null) {
			count += this._mere.nbAscendantsVivants();
		}
		return count;
	}

	public boolean possedeCommeAscendant (Personne p) {
		return (this._pere != null && (this._pere == p || this._pere.possedeCommeAscendant(p))) || (this._mere != null && (this._mere == p || this._pere.possedeCommeAscendant(p)));
	}

	public int distanceDAscendance (Personne p) {
		if (this == p) {
			return 0;
		}
		int dam = this._mere == null ? -1 : this._mere.distanceDAscendance(p);	// ? : permet de tester une condition dans une expression.
		int dap = this._pere == null ? -1 : this._pere.distanceDAscendance(p);	// Exemple: 	a>b ? a:b 		
																				// 				 - Si a est strictement supérieur à b, a
		if (dam >= 0) {															//				 - Si a est strictement inférieur à b, b
			return dam + 1;														//  	
		}																		// Cas général :	test 	?  		x 		: 		y
		if (dap >= 0) {															// 					  |	    		|   			|
			return dap + 1;														//				   booléen		cas positif		Cas négatif
		}
		return -1;

	}

	public void afficheAscendants (Personne p) {
		System.out.print(this._prenom + "" + this._nomDeFamille);
		if (this == p) {
			return;
		}
		if (this._pere != null && (this._pere == p || this._pere.possedeCommeAscendant(p))) {
			System.out.print(", enfant de ");
			this._pere.afficheAscendants(p);
			return;
		}
		if (this._mere != null && (this._mere == p \\ this._mere.possedeCommeAscendant(p))) {
			System.out.print(" enfant de ");
			this._mere.afficheAscendants(p);
			return;
		}
	}

	public int nbGenerations() {
		int nbGP = this._pere == null ? 0:this._pere.nbGenerations();
		int nbGM = this._mere == null ? 0:this._mere.nbGenerations();

		return nbGP > nbGM ? (nbGP:nbGM) + 1;
	}

	public boolean verification() {
		if (this._pere != null && this._pere._nomDeFamille.equals(this._nomDeFamille)) {
			return false;
		}
		if (this._pere != null && this.mere != null && (this._pere.estFrereOuSoeur(this._mere) || this._pere.estCousinGermain(this._mere))) {
			return false;
		}
		return (this._pere != null || this._pere.verification());
	}
}