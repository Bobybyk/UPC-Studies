import java.util.Scanner;

public class Joueur {
	private String nom;
	private Scanner scanReponse;

	public Joueur() {
		this.nom = "Anonyme";
		this.scanReponse = new Scanner (System.in);
	} 

	public void setNom (String nom) {
		this.nom = nom;
	}

	public void finish() {
		this.scanReponse.close();
	}

	public boolean veutJouer() {
		String rep = eval("Voulez-vous jouer (oui/non) ?");
		if (rep.equals("oui")) {
			return true;
		} else return false;
	}

	public String eval (String q) {
		System.out.println(q);
		return this.scanReponse.next();
	}

	public String demanderNom() {
		String rep = eval("Quel est votre nom ?");
		return rep;
	}

	public int[] demanderDimensions() {
		int ha = evalInt("Définir hauteur : ");
		int la = evalInt("Définir largeur : ");
		int[] t = {ha, la};
		return t;
	}

	public int nbrMines() {
		int m = evalInt("Définir nombre de mines : ");
		return m;
	}

	public int evalInt (String q) {
		System.out.println(q);
		return this.scanReponse.nextInt();
	}

	public char demanderAction() {
		boolean check = false;
		String s = "";
		char c = 'e';
		while (check == false) {
			System.out.println("Voulez-vous réveler une case (r) ou placer un drapeau (d) ?");
			s = this.scanReponse.next();
			if (s.equals("r") || s.equals("d")) {
				check = true;
				c = s.charAt(0);
			}
		}
		return c;
	}

	public int[] demandeCoordonnnes() {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println("Coordonnés : ");
		String co = scanReponse.next();
		int ha = 0;
		for (int i = 0 ; i<alpha.length() ; i++) {
			if (alpha.charAt(i) == co.charAt(0)) {
				ha = i+1;
			}
		}
		int la = Character.getNumericValue(co.charAt(1));
		int[] t = {ha-1, la-1};
		if (ha != 0) {
			return t;
		} else {
			return this.demandeCoordonnnes();
		}
	}
}