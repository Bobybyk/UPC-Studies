	public class Memoire {
	private Memoire precedent;
	private Memoire suivant;
	private int valeur;	

	public Memoire() {
		this.precedent = null;
		this.suivant = null;
		this.valeur = 0;
	}

	public Memoire (int t) {
		construct(t, null);
	}

	public Memoire construct (int t, Memoire prec) {
		Memoire mem = new Memoire();
		if (t > 0) {
			this.suivant = construct(t-1, mem);
			this.precedent = prec;
		}
		return mem;
			
	}

	public void inspecte() {
		if (this.suivant != null) {
			System.out.println(this.valeur);
			this.suivant.inspecte();
		} else {
			System.out.println(this.valeur);
		}
	}

	public Memoire getPrecedent() {
		return this.precedent;
	}
	public Memoire getSuivant() {
		return this.suivant;
	}

	public void setValeur(int v) {
		this.valeur = v;
	}
}