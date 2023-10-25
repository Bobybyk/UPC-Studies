public class EntreeSpeciale extends Entree {

	public EntreeSpeciale (Dossier d, String nom) {
		super(d, nom);
	}

	public void Supprimer() {
		System.out.println("Suppression impossible");
	}

	public void remplacer (Element e) {
		System.out.println("Remplacement impossible");
	}
}