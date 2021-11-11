import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Function;


public class MaListe<E> extends LinkedList<E> {

	public void pourChaque (Consumer<?super E> action) {
		for(E x : this) {
			action.accept(x);
		}
	}

	public List<E> filter(Predicate<E> pred) {
		MaListe<E> list = new MaListe();
		for (E e : this) {
			if (pred.test(e)) {
				list.add(e);
			}
		}
		return list;
	}

	public <U> List<U> map(Function<E,U> f) {
		MaListe<U> rep = new MaListe();
		for (E e : this) {
			rep.add(f.apply(e));
		}
		return rep;
	}

	public <U> U fold(U z, BiFunction<U, E, U> f) {
		U acc = z;
		/* for (E e : this) {
			acc = f.apply(acc, x);
		} */
		return acc;
	}
}