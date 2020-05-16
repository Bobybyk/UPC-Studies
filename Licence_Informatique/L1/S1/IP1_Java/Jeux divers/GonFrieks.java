

public class GonFrieks{

	public static String tirage(){
		String choixOrdi = "";
		int nb = (int)(Math.random() * 3);
		if(nb==1){
			choixOrdi = "Pierre";
		}
		else if(nb==2){
			choixOrdi ="Papier";
		}
		else{
			choixOrdi = "Ciseaux";
		}
		return choixOrdi;
	}

	public static String tirageJoueur(){
		String choixJoueur = System.console().readLine();
		return choixJoueur;
	}

	public static String Game(){
		String valeurTest = tirageJoueur();
		String resultat = "";
		String valeurTestOrdi = tirage();
		System.out.println(valeurTestOrdi);
		if(valeurTest.equals("Pierre")&&valeurTestOrdi.equals("Papier")){
			resultat = "O";
			System.out.println(resultat);
		}
		else if(valeurTest.equals("Pierre")&&valeurTestOrdi.equals("Ciseaux")){
			resultat = "J";
			System.out.println(resultat);
		}
		else if(valeurTest.equals("Papier")&&valeurTestOrdi.equals("Ciseaux")){
			resultat = "O";
			System.out.println(resultat);
		}
		else if(valeurTest.equals("Papier")&&valeurTestOrdi.equals("Pierre")){
			resultat = "J";
			System.out.println(resultat);
		}
		else if(valeurTest.equals("Ciseaux")&&valeurTestOrdi.equals("Pierre")){
			resultat = "O";
			System.out.println(resultat);
		}
		else if(valeurTest.equals("Ciseaux")&&valeurTestOrdi.equals("Papier")){
			resultat = "J";
			System.out.println(resultat);
		}
		else{
			resultat="E";
			System.out.println(resultat);
		}
		return resultat;
	}

	public static void chifoumi(int n){
		String scoreGame ="";
		int victoireJ = 0;
		int victoireO = 0;
		for(int i =0; i<n;i++){
			String testGame = Game();
			if(testGame.equals("O")){
				scoreGame=scoreGame+"O";
				victoireO++;
			}
			else if(testGame.equals("J")){
				scoreGame=scoreGame+"J";
				victoireJ++;
			}
			else{
				scoreGame=scoreGame+"E";
			}
		}
		if(victoireJ>victoireO){
			System.out.println("Vous avez gagné avec "+victoireJ+" victoire !");
		}
		else if (victoireO>victoireJ){
			System.out.println("L'ordi a gagné avec "+victoireO+" victoire !");
		}
		else{
			System.out.println("Il y a égalité");
		}
		System.out.println(scoreGame);
	}

	public static void main(String[] args) {
		System.out.print("------------------------------------------\nBienvenue au Pierre-Papier-Ciseaux Party !\n------------------------------------------\n\nCombien de Party voulez-vous faire ? : ");
		int nbrPartie = Integer.parseInt(System.console ().readLine ());
		chifoumi(nbrPartie);
	}
}

