import java.util.HashMap;

public class Collections {
	HashMap<String, Integer> joueurs = new HashMap<>(); 
	

	public Collections(String nom, int num) {
		joueurs.put(nom, num);
	}
}