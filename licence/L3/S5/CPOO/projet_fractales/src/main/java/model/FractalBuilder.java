package model;

public class FractalBuilder {
	
	private Complex complexConstant = new Complex(0.3, 0.5);
	private FractalDefinitionDomain domain = new FractalDefinitionDomain(-1, 1, -1, 1);
	private double gap = 0.01;
	
	public FractalBuilder setComplexConstant(Complex c) {
		this.complexConstant = c;
		return this;
	}
	
	public FractalBuilder setDefinitionDomain(FractalDefinitionDomain d) {
		this.domain = d;
		return this;
	}
	
	public FractalBuilder setGap(double newGap) {
		this.gap = newGap;
		return this;
	}
	
	public Fractal buildJulia() {
		return new JuliaFractal(this);
	}
	
	public Fractal buildMandelbrot() {
		return new MandelbrotFractal(this);
	}
	
	//Getters
	public Complex getComplexConstant() {
		return this.complexConstant;
	}
	
	public FractalDefinitionDomain getDomain() {
		return this.domain;
	}
	
	public double getGap() {
		return this.gap;
	}
}
