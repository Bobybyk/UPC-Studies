package model;

public class MandelbrotFractal extends Fractal{

	public MandelbrotFractal(FractalBuilder fb) {
		super(fb);
	}

	protected Complex next() {
		if(this.domain.contains(lastComputed)) {
			Complex zn = f(complexConstant,lastComputed);
			
			if(this.domain.contains((lastComputed.plus(new Complex(0, gap))))) {
				this.lastComputed = lastComputed.plus(new Complex(0, gap));
			} else {
				this.lastComputed = new Complex(this.lastComputed.getRealPart() + gap,this.domain.getMin().getImaginaryPart());
			}
				
			return lastComputed;
		}
		return null;
	}

	protected int getCurrentDivergenceIndex() {
		int i = 0;
		Complex lastValue = f(complexConstant,lastComputed);
		while((i < 1000) && (lastValue.getModulus() < 2)) {
			lastValue = f(complexConstant,lastValue);
		}
		return i;
	}

}
