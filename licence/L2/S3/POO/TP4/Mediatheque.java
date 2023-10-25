import java.util.LinkedList;
import java.util.ArrayList;

public class Mediatheque {
	private final LinkedList<Media> basesDeDonnees;

	public Mediatheque() {
		this.basesDeDonnees = new LinkedList<Media>();
	}

	public static void main (String[] args) {
		Mediatheque me = new Mediatheque();

		Media m = new Media("Livre1");
		Media m2 = new Media("Livre2");
		Media m3 = new Media("Livre3");
		Media m4 = new Media("Livre4");

		me.ajouter(m2);
		me.ajouter(m4);
		me.ajouter(m3);
		me.ajouter(m);

		System.out.println(me.toString());

		Predicat p = new Predicat();

		System.out.println("Media pour p, vrai : " + me.recherche(p).toString());
	}

	public ArrayList<Media> recherche (Predicat p) {
		ArrayList<Media> m = new ArrayList<Media>();
		for (int i = 0 ; i < m.size() ; i++) {
			if (p.estVrai(this.basesDeDonnees.get(i))) {
				m.add(this.basesDeDonnees.get(i));
			}
		}
		return m;
	}

	public void tousLesDictionnaires() {
		for (Media s : this.basesDeDonnees) {
			if (s instanceof Dictionnaire) {
				System.out.println(s);
			}
		}
	}

	public void ajouter (Media doc) {
		int i = 0;
		for (Media m : this.basesDeDonnees) {
			if (m.plusPetit(doc)) {
				i++;
			}
		}
		this.basesDeDonnees.add(i,doc);
	}

	public String toString() {
		String tmp = "";
		for (int i = 0 ; i<this.basesDeDonnees.size() ; i++) {
			tmp += "Media " + i + " : " + this.basesDeDonnees.get(i) + "\n";
		}
		return tmp;
	}

}