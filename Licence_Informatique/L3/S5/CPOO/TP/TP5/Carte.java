public abstract class Carte {

	public final static class CarteNormale extends Carte {
		private Enseigne enseigne;
		private Valeur valeur;

		public CarteNormale(Enseigne e, Valeur val) {
			this.enseigne = e;
			this.valeur = val;
		}
	}

	public final static class Joker extends Carte {}
}