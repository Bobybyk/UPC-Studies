import java.util.Random;

public class Main {
	public static void main (String[] args) {
		mouvement(10, 10, 7, 7, 2, 10);
	}

	public static void mouvement(int largeur, int longeur, int xF, int yF, int o, int nbrM) {
		Grille gr = new Grille(largeur, longeur);
		if (xF >= largeur) { // si position x fourmi supérieure à la largeur de la grille : x = largeur max (pas de test pour x inférieur à la largeur min de la grille car x = -1, -2, ... peu probable)
			xF = largeur-1;
		}
		if (yF >= longeur) { // si position y fourmi supérieure à la longueur de la grille : y = longueur max (pas de test pour x inférieur à la largeur min de la grille car y = -1, -2, ... peu probable)
			yF = longeur-1;
		}
		Fourmi f = new Fourmi(xF, yF, o, gr);
		gr.dessine();
		for (int i = 0 ; i<nbrM ; i++) {
			if (f.unMouvement() == false) {
				break;
			} else {
				gr.dessineAvecFourmi(f);
				f.afficheToi();
				System.out.println("");
			}
		}
	}

	public static void multiFourmis (int nbrFourmis, int la, int lo) {
		Random rand = new Random();

		int xF = rand.nextInt(10+1);
		int yF = rand.nextInt(10+1);
		int o = rand.nextInt(4);
	}  
}