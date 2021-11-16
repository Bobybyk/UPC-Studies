import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.HashMap;

public class Exo1 {

	public static <K, V> Map<K, V> creeMap(List<K> l, Function<K, V> f) {
		Map<K, V> map = new HashMap<K, V>();
		for (K k : l) {
			V v = f.apply(k);
			map.put(k, v);
		}
		return map;
	}

	public static void main(String[] args) {
		// On crée une liste de String
		List<String> l1 = Arrays.asList("Hello", "World", "Bonjour", "Monde");
		// On crée une fonction permettant de donner la taille de la String
		Function<String, Integer> func = text -> text.length();
		// On stock la Map retourner par creerMap de la liste de String l1 et du retour de la fonction func
		Map<String, Integer> map = creeMap(l1, func);
		// On affiche le couple k,v pour chaque élément de Map
		// On est donc sensé afficher chaque mot suivi de sa longueur 
		map.forEach((t,r) -> System.out.println(t + " : " + r));
	}
}