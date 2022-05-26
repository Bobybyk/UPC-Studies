package model;

public class FractalDefinitionDomain {

	private double x1;
	private double x2;
	
	private double y1;
	private double y2;
	
	public FractalDefinitionDomain(double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public boolean contains(Complex c) {
		return (c.getRealPart() >= x1) && (c.getRealPart() <= x2) && (c.getImaginaryPart() >= y1) && (c.getImaginaryPart() <= y2);
	}
	
	public Complex getMin() {
		return new Complex(x1,y1);
	}
	
	public Complex getMax() {
		return new Complex(x2,y2);
	}
	
	public String toString() {
		return x1+" "+y1+"\n"+x2+" "+y2;
	}
}
