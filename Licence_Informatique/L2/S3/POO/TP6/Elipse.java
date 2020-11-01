import java.lang.Math.*;

public class Elipse extends Figure {
	private final double grand_rayon, petit_rayon;


	public Elipse (int x, int y, double grand_rayon, double petit_rayon) {
		super(x, y);
		this.grand_rayon = grand_rayon;
		this.petit_rayon = petit_rayon;
	}

	public void affiche() {

	}

	public double estDistanDe(Figure fig) {
		return Math.abs(fig.getPosX()*fig.getPosY()-super.getPosX()*super.getPosY());
	}

	public double surface() {
		return Math.PI*this.grand_rayon*this.petit_rayon;
	}
}