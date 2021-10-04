public class Carre extends Rectangle {

	public Carre(double ax, double ay, double aDeg, double length) {
		super(ax, ay, aDeg, length, length);
	}

	public void setABLength(double length) {
		super.setABLength(length);
		super.setBCLength(length);
	}
	public void setBCLength(double length) {
		super.setABLength(length);
		super.setBCLength(length);
	}
}