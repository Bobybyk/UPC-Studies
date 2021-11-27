public class Triangle extends Figure {
	private final double base, hauteur;

	public Triangle (int x, int y, double base, double hauteur) {
		super(x, y);
		this.base = base;
		this.hauteur = hauteur;
	}

	public void affiche() {
		
	}

	public double estDistanDe(Figure fig) {
		return Math.abs(fig.getPosX()*fig.getPosY()-super.getPosX()*super.getPosY());
	}

	public double surface() {
		return this.base*this.hauteur/2;
	}
}