public class Lanceur {

	public static void main (String[] args) {
		System.out.println("##### Démineur #####");
		Joueur joueur = new Joueur();
		if (joueur.veutJouer()) {
			joueur.setNom(joueur.demanderNom());
			int[] c = joueur.demanderDimensions();
			int nbMines = joueur.nbrMines();
			Plateau p = new Plateau(c[0], c[1], nbMines);
			Jeu j = new Jeu(joueur, p);
			j.jouer();
		} 
		joueur.finish();
	}
}