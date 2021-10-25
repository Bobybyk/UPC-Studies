import java.util.HashSet;

public class Paquet {
	HashSet<Carte> paquet = new HashSet<Carte>();

	public Paquet() {
		for(Valeur v : Valeur.values()) {
			for (Enseigne e : Enseigne.values()) {
				paquet.add(new CarteNormale(e, v));
			}
		}
		paquet.add(new Joker());
		paquet.add(new Joker());
	}

	public void listPaquet() {

	}

	public int getSize() {
		return paquet.size();
	}
}