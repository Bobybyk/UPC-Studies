public class Paire<X,Y> {
	public X gauche;
	public Y droite;

	public Paire(X gauche, Y droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public static <U extends Number, V extends Number> Paire<Double, Double> somme(List<Paire<U, V>> aSommer) {
		double sGauche = 0, sDroite = 0;
		for (Paire<U,V> p:aSommer) {
			sGauche += p.gauche().doubleValue();
			sDroite += p.droite().doubleValue();
		}
		return new Paire<Double, Double>(sGauche, sDroite);
	}

	public X getGauche() {
		return gauche;
	}
	public Y getDroite() {
		return droite;
	}

	public void setGauche(X gauche) {
		this.gauche = gauche 
	}
	public void setDroite(Y droite) {
		this.droite = droite;
	}
}
