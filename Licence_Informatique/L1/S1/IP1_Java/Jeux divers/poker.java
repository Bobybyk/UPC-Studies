import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class poker {

	public static void affichage (int x) {

		Scanner in = new Scanner(System.in);
		String launch = "";

		if (x == 1) {
			x = 0;
			System.out.println("-------------------------\n-------------------------\nPoker aux dés\n-------------------------");
			System.out.println("-------------------------\nr : pour voir les régles\n-------------------------\nq : pour quitter ce menu\n-------------------------");
			
			launch = in.nextLine();
   
    	        	if(launch.charAt(0) == 's'){
        	    		System.out.println("-------------------------\nRégles\n-------------------------\n");
					}
       	}	
			
    }

    public static int[] joueurUn (int []tab, int x) {

    	if (x == 0) {
    		//int []tab = new int [5];
    		int a = 0;
    		for (int i = 0 ; i< 5 ; i ++) {
    			a = ThreadLocalRandom.current().nextInt(1, 6 + 1);
    			tab[i] = a;
    		}
		}

		if (x == 1) {

		}
		return tab;
    }

	public static void main (String[] args) {

		Scanner in = new Scanner(System.in);
		String launch = "";
		int a;
		int w = 0;

		affichage(1);
		System.out.println("-------------------------\n[entrer] : pour démarrer la partie\n-------------------------\n");
		launch = in.nextLine();
		System.out.println("-------------------------\nDés Joueur 1 : " + java.util.Arrays.toString(joueurUn(new int [5] , 0)));
		System.out.println("-------------------------");
		System.out.println("-------------------------\nc : pour continuer\na : pour relancer tous les dés\n1 : pour relancer dé 1\n2 : pour relancer dé2\n3 : pour relancer dé 3\n4 : pour relancer dé 4\n5 : pour relancer dé5\n-------------------------\n");

		while (w == 0) {
			launch = in.nextLine();
			a = in.nextInt();
			if (launch.charAt(0)  == 'c') {
				w = 1;
				// System.out.println("-------------------------\nDés Joueur 1 : " + java.util.Arrays.toString(joueurUn(new int [5] , 1)));
			}
			if (launch.charAt(0) == 'a') {
				w = 1;
			}
			if (a == 1) {
				w = 1;
			}
			if (a == 2) {
				w = 1;
			}
			if (a == 3) {
				w = 1;
			}
			if (a == 4) {
				w = 1;
			}
			if (a == 5) {
				w = 1;
			}
		}

}

}