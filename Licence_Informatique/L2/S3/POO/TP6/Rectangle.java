public class Rectangle extends Figure {
	private final double largeur, hauteur;

	public Rectangle (int x, int y, double l, double h) {
		super(x, y);
		this.largeur = l;
		this.hauteur = h;
	}

	public void affiche() {

	}	

	public double estDistanDe(Figure fig) {
		return Math.abs(fig.getPosX()*fig.getPosY()-super.getPosX()*super.getPosY());
	}

	public double surface() {
		return this.largeur*this.hauteur;
	}
}