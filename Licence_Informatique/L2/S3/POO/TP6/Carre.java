public class Carre extends Figure {
	private final double cote;

	public Carre (int x, int y, double c) {
		super(x, y);
		this.cote = c;
	}

	public void affiche() {
		
	}

	public double estDistanDe(Figure fig) {
		return Math.abs(fig.getPosX()*fig.getPosY()-super.getPosX()*super.getPosY());
	}

	public double surface() {
		return this.cote*this.cote;
	}
}