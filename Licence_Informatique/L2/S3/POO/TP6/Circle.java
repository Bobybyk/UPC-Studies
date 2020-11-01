import java.lang.Math.*;

public class Circle extends Figure {
	private final double rayon;

	public Circle (int x, int y, double rayon) {
		super(x, y);
		this.rayon = rayon;
	}

	public void affiche() {

	}

	public double estDistanDe(Figure fig) {
		return Math.abs(fig.getPosX()*fig.getPosY()-super.getPosX()*super.getPosY());
	}

	public double surface() {
		return Math.PI*this.rayon*this.rayon;
	}

}