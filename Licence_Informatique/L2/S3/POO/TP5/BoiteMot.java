public class BoiteMot extends Boite{
	private String mot;

	public BoiteMot(String next) {
		this.mot = next;
	}
	public String toString() {
		return mot;
	}

	public int length() {
		return mot.length();
	}
}