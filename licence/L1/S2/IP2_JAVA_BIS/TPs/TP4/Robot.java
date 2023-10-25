public class Robot {
	private final char nom; //lettre entre 'a' et 'z'
	private int energie;
	private final String texte; //ce qu'il doit dire

	public Robot (char nom, String parole) {
		this.nom = nom;
		energie = 10 + (int)(Math.random()*11); //on donne une énergie entre 10 et 20
		texte = parole;
	}

	public String description() {
		return "Robot " + this.nom + " dit " + this.texte + " quand il parle et a " + this.energie + " points d'énergie";
	}

	public boolean nomCorrect() {
		return (!(Character.isUpperCase(this.nom)));
	}

	public void chante() {
		String a = "";
		for (int i = 0 ; i < (int) this.nom - 96 ; i++) {
			a = a + this.texte;
		}
		System.out.println(a);
	}

	public char getNom() {
		return this.nom;
	}
	public int getEnergie() {
		return this.energie;
	}
	public String getTexte() {
		return this.texte;
	}
}