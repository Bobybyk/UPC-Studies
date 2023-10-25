package model;

public class Complex {

	//Attributes
	private final double realPart;
	private final double imaginaryPart;

	//Constructors 
	public Complex(double real, double imaginary) {
		this.realPart = real;
		this.imaginaryPart = imaginary;
	}

	//Functions 
	public Complex plus(Complex x) {
		double real = this.realPart + x.realPart;
		double imaginary = this.imaginaryPart + x.imaginaryPart;
		return new Complex(real,imaginary);
	}

	public Complex times(Complex x) {
		double real = (this.realPart * x.realPart) - (this.imaginaryPart * x.imaginaryPart);
		double imaginary = (this.realPart * x.imaginaryPart) + (this.imaginaryPart * x.realPart);
		return new Complex(real,imaginary);
	}
	
	//Getters
	public double getRealPart() {
		double real = this.realPart;
		return real;
	}
	
	public double getImaginaryPart() {
		double im = this.imaginaryPart;
		return im;
	}
	
	public double getModulus() {
		return Math.sqrt(Math.pow(this.realPart,2) + Math.pow(this.imaginaryPart, 2));
	}

	//Tests
	public boolean equals(Complex x) {
		return (this.imaginaryPart == x.imaginaryPart) && (this.realPart == x.realPart);
	}

	//toString
	public String toString() {
		String ret = realPart + "";
		if (this.getImaginaryPart() >= 0) ret += "+" ;
		ret += imaginaryPart + "i";
		return ret;
	}
}