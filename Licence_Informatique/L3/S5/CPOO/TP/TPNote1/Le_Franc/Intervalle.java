import java.util.function.BiPredicate;
import java.util.function.Function;

public class Intervalle {
	public int a, b;

	public Intervalle(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public boolean tester(BiPredicate<Intervalle, Intervalle> p, Intervalle autre) {
		return p.test(this, autre);
	}

	public Intervalle nouveau(Function<Intervalle, Intervalle> f) {
		return f.apply(this);
	}

	public String toString() {
		return "[" + a + "," + b + "]";
	}

}