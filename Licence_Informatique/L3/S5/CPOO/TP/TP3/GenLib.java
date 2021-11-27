import java.util.Random;
import java.util.Scanner;

public class GenLib {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Select function :\n(1) generate random\n(2) generate arithm\n(3) generate geo\n(4) fibo");
		switch(s.nextInt()) {
			case 1: System.out.print("Random function : ") ; System.out.println(GenLib.generateAlea(5).suivant());
			case 2:
			case 3:
			case 4:
			default:
		}
	}

	private GenLib() {

	}

	private static class GenerateArithm implements Generateur {
		int r;
		int x = 0;

		private GenerateArithm(int r) {
			this.r = r;
		}

		public int suivant() {
			x++;
			return r*x;
		}
	}

	public static Generateur buildGenerateurArithm(int r) {
		return new GenerateArithm(r);
	}

	public static Generateur aleatoire(int n) {
		return generateAlea(n);
	} 

	// classe anonyme
	public static Generateur generateAlea(int n) {
		return new Generateur() {
			int random;

			public int suivant() {
				return new Random().nextInt(n);
			}
		};
	}

}