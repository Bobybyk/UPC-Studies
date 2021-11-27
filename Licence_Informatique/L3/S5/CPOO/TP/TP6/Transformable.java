import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface Transformable<T> {
	T getElement();
	public void transform(UnaryOperator<T> trans);
}