public class Maison {
	private Espion habitant;
	private Maison ligneVers;
	private int id;
	private static int nbID = 1;

	public Maison (Espion h, Maison l) {
		this.id = nbID;
		nbID++;
		this.habitant = h;
		this.ligneVers = l;
	}
	public Maison (Espion h) {
		this(h, null);
	}

	public String description() {
		String s = this.ligneVers != null?"vers maison " + this.ligneVers.getID():"non connectée";
		return "maison " + this.id + " : habitée par " + this.habitant.description() + ", ligne " + s;
	}

	public int getID() {
		return this.id;
	}
}