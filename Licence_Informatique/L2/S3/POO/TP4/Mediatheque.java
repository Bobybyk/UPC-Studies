import java.util.*;

public class Mediatheque {
	LinkedList<Media> basesDeDonnees;

	public Mediatheque() {
		this.basesDeDonnees = null;
	}

	public static void main (String[] args) {
		
	}

	public void ajouter (Media doc) {
		this.basesDeDonnees.add(doc);
	}
}