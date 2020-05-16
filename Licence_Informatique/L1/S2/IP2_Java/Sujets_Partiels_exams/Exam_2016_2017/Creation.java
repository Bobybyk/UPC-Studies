public class Creation {
	private Ingredient _ingredient;
	private Sandwich _noeudGauche;
	private Creation _noeudDroit;
	private Creation _neoudPere;
	private Sandwich _sandwich;

	public Creation (Ingredient ingr) {
		this._ingredient = ingr;
		this._noeudGauche = null;
		this._noeudDroit = null;
		this._neoudPere = null;
		this._sandwich = null;
		
	}

	public Creation (Ingredient ingr, Creation noeudGauche, Creation noeudDroit, Creation noeudPere) {
		this._ingredient = ingr;
		this._noeudGauche = noeudGauche;
		this._noeudDroit = noeudDroit;
		this._neoudPere = noeudPere;
		this._sandwich = null;
	}

	public Creation (Ingredient ingr, Creation noeudGauche, Creation noeudDroit, Creation noeudPere, Creation sandwich) {
		this._ingredient = ingr;
		this._noeudGauche = noeudGauche;
		this._noeudDroit = noeudDroit;
		this._neoudPere = noeudPere;
		this._sandwich = sandwich;
	}
}