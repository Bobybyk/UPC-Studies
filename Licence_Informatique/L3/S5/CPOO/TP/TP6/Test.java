import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Test {
	public static void main(String[] args) {
		MaListe <Integer> li = new MaListe();
		for (int i = 0 ; i<10 ; i++) {
			li.add(i);
		}

		li.pourChaque(x -> System.out.println((x)));
		li.pourChaque(System.out::println);

		Consumer<Object> c = new Consumer<Object>() {
			int n = 1;
			public void accept(Object x) {
				System.out.println(n++ + " : " + x);
			}
		};

		li.pourChaque(c);
		MaListe<Personne> liPer = new MaListe();

		for (int i = 0 ; i<10 ; i++) {
			liPer.add(new Personne("Personne n°" + i));
		}

		liPer.pourChaque(c);

		List<Integer> filteredList = li.filter(x -> x<5);
		filteredList.pourChaque(c);
	}
}