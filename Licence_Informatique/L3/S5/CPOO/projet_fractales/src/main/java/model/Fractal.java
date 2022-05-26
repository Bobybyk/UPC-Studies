package model;

public abstract class Fractal {
	
	protected final Complex complexConstant;
	protected final FractalDefinitionDomain domain;
	protected double gap;
	protected Complex lastComputed;
	
	public Fractal(FractalBuilder fb) {
		this.complexConstant = fb.getComplexConstant();
		this.domain = fb.getDomain();
		this.gap = fb.getGap();
		this.lastComputed = fb.getDomain().getMin();
	}
	
	protected Complex f(Complex z, Complex c) {
		return z.times(z).plus(c);
	}
	
	protected abstract int getCurrentDivergenceIndex();
	
	protected abstract Complex next();
	
	/*
	 * returns the divergence index calculated from lastComputed
	 * and returns -1 if all numbers in the definition domain have been calculated
	 */
	public int getNextDivergenceIndex() {		
		if (next() != null)
			return getCurrentDivergenceIndex();
		else
			return -1;
	}
	
	public FractalDefinitionDomain getDomain() {
		return this.domain;
	}
	
	public double getGap() {
		return this.gap;
	}
	
	public Complex getLastComputed() {
		return this.lastComputed;
	}
	
	public Complex getComplexConstant() {
		return this.complexConstant;
	}
}
