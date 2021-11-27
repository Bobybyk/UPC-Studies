public class Eleve {
	private Objets obj;
	private Eleve suivant;

	public Eleve () {
		this.obj = null;
		this.suivant = null;
	}
	public Eleve (Objets o, Eleve e) {
		this.obj = o;
		this.suivant = e;
	}
	public Eleve (Objets o) {
		this(o, null);
	}
	
	public Eleve getSuivant () {
		return this.suivant;
	}
	public Objets getObjet() {
		return this.obj;
	}
	public Eleve getEleve() {
		return this.suivant
	}
	public void setSuivant(Eleve s) {
		this.suivant = s;
	}
}