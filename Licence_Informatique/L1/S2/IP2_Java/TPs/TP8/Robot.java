import java.util.Random;

public class Robot {
	static int nbRob; //nombre de robots
	private int id; //identifiant
	private int np; //nbr paroles
	static int vId = 0; //vérif' IDs existants
	private char nom;

	public Robot (char nom, String text) {

		this.id = this.vId;
		this.vId++;
		
		this.np = text.length();

		this.nbRob++;

		this.nom = nom;

	}

	public boolean finiDeParler() {
		if (this.np <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int parle (int n) {
		this.np = this.np - n;
		return np;
	}

	public void description() {
		System.out.println("Robot id : " + this.id);
	}

	public int getId() {
		return this.id;
	}
	public char getNom() {
		return this.nom;
	}
	public int getNbRob() {
		return this.nbRob;
	}

	public void setNbRob() {
		this.nbRob--;
	}
}