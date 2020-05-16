import java.util.Scanner;
import java.util.Random;

public class RéseauSocial {


	public static int[][] CreateGraph (int u, int a) {

		Random rand = new Random();	

		int  r = rand.nextInt(2) + 1;

		int[][] tab = new int [u][a];

		for (int i = 0 ; i<u ; i++) {
			
			for (int j = 0 ; j<a ; j++) {
				
				r = rand.nextInt(2) + 1;
				tab[i][j] = r-1;

			}

		}

		for (int k = 0 ; k<u ; k++) {
			System.out.print("L'utilisateur ");
			System.out.print(k+1);
			System.out.print(" possède : ");
			int amis = 0;
			for (int l = 0 ; l<a ; l++) {
				if (tab[k][l] == 1) {
					amis = amis + 1;
				}
			}
				System.out.println(amis + " amis");
		}

		return tab;

	}


	public static void main (String[] args) {

		Scanner in = new Scanner(System.in);
		String launch = "";
		System.out.println("-------------------------------------------------------");
		System.out.println("Jeu des amis :");
		System.out.println("");
		System.out.println("	Entrez :");
		System.out.println("		a : pour lancer le jeu en aléatoire/automatique");
		launch = in.nextLine();

		if (launch.charAt(0)=='a') {

			Random rand = new Random();	


			int  u = rand.nextInt(5) + 1;
			int  a = rand.nextInt(100) + 1;
		
			System.out.println("Tableau des utilisateurs : " + (java.util.Arrays.deepToString(CreateGraph(u, a))));

		}

}




}