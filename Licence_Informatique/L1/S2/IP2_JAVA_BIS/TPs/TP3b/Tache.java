public class Tache {
	private final String nom;
	private Tache[] necessairePour;
	private static int cNum = 0;
	private final int numero;

	public Tache (String n) {
		this.nom = n;
		this.necessairePour = null;
		cNum++;
		this.numero = cNum;
	}

	public void estNecessairePour (Tache t) {
		Tache[] tTa;
		if (this.necessairePour == null) {
			tTa = new Tache[1];
			tTa[0] = t;
		} else {
			tTa = new Tache[necessairePour.length + 1];
			for (int i = 0 ; i<necessairePour.length-1 ; i++) {
				tTa[i] = this.necessairePour[i];
			}
			tTa[tTa.length] = t;
		}
		this.necessairePour = tTa;
	}

	public void affiche() {
		System.out.println("Tache " + this.numero + " : " + this.necessairePour[this.numero].nom + ". Est nécessaire avant les taches " + this.necessairePour[this.numero].nom + ", " + this.necessairePour[this.numero].nom);
	}
}