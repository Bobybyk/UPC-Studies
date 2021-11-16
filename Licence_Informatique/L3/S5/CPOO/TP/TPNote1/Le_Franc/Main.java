import java.util.function.BiPredicate;
import java.util.function.Function;

public class Main {
	
	public static void main(String[] args) {

		BiPredicate<Intervalle,Intervalle> biPred = (aIntervalle, bIntervalle) -> {
			return aIntervalle.a>=bIntervalle.a && aIntervalle.b<=bIntervalle.b;
		};

		Function<Intervalle, Intervalle> func = inter -> {

			// pas conforme aux demandent (manque de temps)
			return new Intervalle(0, inter.b-inter.a);

		};

		// création de deux intervalles
		Intervalle a = new Intervalle(5, 6);
		Intervalle b = new Intervalle(3, 20);

		// test du predicat sur l'intervalle a et l'intervalle b
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(a.tester(biPred, b) + "\n");


		Intervalle[] tabInter = {a, b};

		// création de nouveaux intervalles à partir de a et b
		for (int i = 0 ; i < tabInter.length ; i++) {
			System.out.println(tabInter[i].nouveau(func).toString());
		}
	}
}
