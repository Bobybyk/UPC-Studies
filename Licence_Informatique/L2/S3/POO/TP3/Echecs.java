public class Echecs {
	private boolean joueur;

	public Echecs () {
		this.joueur = true;
	}

	public void jouerTour (Deplacement d, boolean joueur, Plateau p) {
		
	}

	public static void main (String[] args) {
		Plateau p = new Plateau(8, 8);

		Tour tB = new Tour(true);
		Tour tBDeux = new Tour(true);
		Cavalier cB = new Cavalier(true);
		Cavalier cBDeux = new Cavalier(true);
		Fou fB = new Fou(true);
		Fou fBDeux = new Fou(true);
		Dame dB = new Dame(true);
		Roi rB = new Roi(true);
		Pion pBUn = new Pion(true);
		Pion pBDeux = new Pion(true);
		Pion pBTrois = new Pion(true);
		Pion pBQuatre = new Pion(true);
		Pion pBCinq = new Pion(true);
		Pion pBSix = new Pion(true);
		Pion pBSept = new Pion(true);
		Pion pBHuit = new Pion(true);


		Tour tN = new Tour(false);
		Tour tNDeux = new Tour(false);
		Cavalier cN = new Cavalier(false);
		Cavalier cNDeux = new Cavalier(false);
		Fou fN = new Fou(false);
		Fou fNDeux = new Fou(false);
		Dame dN = new Dame(false);
		Roi rN = new Roi(false);
		Pion pNUn = new Pion(false);
		Pion pNDeux = new Pion(false);
		Pion pNTrois = new Pion(false);
		Pion pNQuatre = new Pion(false);
		Pion pNCinq = new Pion(false);
		Pion pNSix = new Pion(false);
		Pion pNSept = new Pion(false);
		Pion pNHuit = new Pion(false);


		p.remplirCase(7, 0, tB);
		p.remplirCase(7, 1, cB);
		p.remplirCase(7, 2, fB);
		p.remplirCase(7, 3, dB);
		p.remplirCase(7,4, rB);
		p.remplirCase(7, 5, fBDeux);
		p.remplirCase(7, 6, cBDeux);
		p.remplirCase(7, 7, tBDeux);

		p.remplirCase(6, 0, pBUn);
		p.remplirCase(6, 1, pBDeux);
		p.remplirCase(6, 2, pBTrois);
		p.remplirCase(6, 3, pBQuatre);
		p.remplirCase(6, 4, pBCinq);
		p.remplirCase(6, 5, pBSix);
		p.remplirCase(6, 6, pBSept);
		p.remplirCase(6, 7, pBHuit);

		p.remplirCase(0, 0, tN);
		p.remplirCase(0, 1, cN);
		p.remplirCase(0, 2, fN);
		p.remplirCase(0, 3, dN);
		p.remplirCase(0, 4, rN);
		p.remplirCase(0, 5, fNDeux);
		p.remplirCase(0, 6, cNDeux);
		p.remplirCase(0, 7, tNDeux);

		p.remplirCase(1, 0, pNUn);
		p.remplirCase(1, 1, pNDeux);
		p.remplirCase(1, 2, pNTrois);
		p.remplirCase(1, 3, pNQuatre);
		p.remplirCase(1, 4, pNCinq);
		p.remplirCase(1, 5, pNSix);
		p.remplirCase(1, 6, pNSept);
		p.remplirCase(1, 7, pNHuit);

		p.afficher();
	}
}