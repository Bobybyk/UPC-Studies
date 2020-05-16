import java.util.Random;

public class Construct {

	public Construct (int[] tab1) {

		Random rand = new Random();

		System.out.println("class Construct : je remplie tConstruct d'entiers aléatoires");
		for (int i = 0 ; i<tab1.length ; i++) {
			tab1[i] = rand.nextInt(10) + 1;
			System.out.println("class Construct : " + java.util.Arrays.toString(tab1));
		}
	}

	// public stackPile (int[] tPile) {
		
	// 	// Stack <integer> p = new Stack <integer>();
	// 	// int x = 0;

	// 	// System.out.println("");

	// 	// while (p.empty()) {
	// 	// 	x = tPile.pop(p);
	// 	// }
	
	// }

// 	public Pile (String n, int l, int[] c) {
// 		name = n;
// 		length = l;
// 		content = c;
// 	}
// }


/*Pile nvlPile = new Pile();
Pile ccPile = new Pile("Pile1", 5, {0,544,5,6,7});*/

}