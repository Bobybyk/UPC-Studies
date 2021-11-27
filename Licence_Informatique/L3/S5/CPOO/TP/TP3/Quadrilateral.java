import java.awt.geom.Point2D.Double;

public class Quadrilateral {
	private final Double a;
	private final Double b;
	private final Double c;
	private final Double d;

	public Quadrilateral(Double a, Double b, Double c, Double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public double surface() {
		Double ac = new Double(c.getX() - a.getX(), c.getY() - a.getY()); 
		
		Double bd = new Double(d.getX() - b.getX(), d.getY() - b.getY()); 
		return((1/2)*(ac.getX()*bd.getY() - ac.getY()*ac.getY()));
	}

	public Double getA() {
		return a;
	}
	public Double getB() {
		return b;
	}
	public Double getC() {
		return c;
	}
	public Double getD() {
		return d;
	}
}