public class Salle {
	private boolean videoProjecteur;
	private final int id;
	private static int nbId = 0;
	private int capacite;
	private Couloir couloir;

	Salle (boolean vid, int capa) {
		this.id = nbId++;
		this.videoProjecteur = vid;
		this.capacite = capa;
	}

	public boolean getVideo() {
		return this.videoProjecteur;
	}
	public Couloir getCouloir() {
		return this.couloir;
	}
}