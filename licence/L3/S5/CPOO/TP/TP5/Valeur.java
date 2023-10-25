public enum Valeur {
	AS(true), DEUX(false), TROIS(false), QUATRE(false), CINQ(false), SIX(false), SEPT(false), HUIT(false), NEUF(false), DIX(false), VALET(true), DAME(true), ROI(true);
	public final boolean figure;
	
	private Valeur(boolean figure) {
		this.figure = figure;
	}
}