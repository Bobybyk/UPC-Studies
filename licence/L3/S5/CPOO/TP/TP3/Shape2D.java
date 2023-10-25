interface Shape2D {
	double perimeter();
	double surface();

	/**
	* Méthode servant juste au test. Ne doit pas servir dans le programme final.
	* @return true si la figure a bien les propriétés qu'elle prétend avoir
	*/
	default boolean checkInvariants() { return true; }
}