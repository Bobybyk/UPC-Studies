public class Jeu {
	private Joueur joueur;
	private Plateau plateau;

	public Jeu (Joueur j, Plateau p) {
		this.joueur = j;
		this.plateau = p;
	}	

	public void jouer() {
		while (!this.plateau.jeuPerdu() && !this.plateau.jeuGagne()) {
			this.plateau.afficheCourant();
			this.plateau.afficheTout();
			if (joueur.demanderAction() == 'r') {
				int[] t = this.joueur.demandeCoordonnnes();
				this.plateau.revelerCase(t[0], t[1]);
			}
			else {
				int[] t = this.joueur.demandeCoordonnnes();
				this.plateau.drapeauCase(t[0], t[1]);
			}
		}
	}
}