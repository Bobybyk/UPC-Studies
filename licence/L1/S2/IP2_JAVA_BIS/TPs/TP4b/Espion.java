public class Espion {
	private String vraiNom;
	private boolean loyal;
	private int id;
	private static int nbID = 1;

	public Espion (String n, boolean loyal) {
		this.vraiNom = n;
		this.loyal = loyal;
		this.id = nbID;
		nbID++;
	}
	public Espion (String n) {
		this.vraiNom = n;
		this.loyal = true;
		this.id = nbID;
		nbID++;
	}

	public String description() {
		String s = this.loyal == true?" loyal":" déloyal";
		return "espion " + this.id + s;
	}
}