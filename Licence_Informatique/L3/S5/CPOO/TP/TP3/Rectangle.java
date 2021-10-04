public class Rectangle {
	private double ax;
	private double ay;
	private double aDeg;
	private double abLength;
	private double bcLength;

	public Rectangle(double ax, double ay, double aDeg, double abLength, double bcLength) {
		this.ax = ax;
		this.ay = ay;
		this.aDeg = aDeg;
		this.abLength = abLength;
		this.bcLength = bcLength;
	}


	public double getAX() {
		return ax;
	}
	public double getAY() {
		return ay;
	}
	public double getADeg() {
		return aDeg;
	}	
	public double getABLength() {
		return abLength;
	}
	public double getBCLength() {
		return bcLength;
	}

	public void setABLength(double l) {
		abLength = l;
		// On override ce setteur dans Carré. Condition instanceof inutile
		if (this instanceof Carre) {
			bcLength = l;
		}
	}
	public void setBCLength(double l) {
		this.bcLength = bcLength;
		// On override ce setteur dans Carré. Condition instanceof inutile
		if (this instanceof Carre) {
			abLength = l;
		}
	}
}