import java.util.function.Function;
import java.util.function.UnaryOperator;

public class EntierTransformable implements Transformable<Integer> {

	private int element;

	public EntierTransformable(int element) {
		this.element = element;
	}

	@Override
	public Integer getElement() {
		return element;
	}
	
	@Override
	public void transform(UnaryOperator<Integer> trans) {
		element = trans.apply(element);
	}	
}