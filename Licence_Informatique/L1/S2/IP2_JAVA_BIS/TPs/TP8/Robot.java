public class Robot {
	static int nbRob;
	private int id;
	private String texte;
	private int np;

	public Robot (String texte) {
		nbRob++;
		this.id = nbRob;
		this.texte = texte;
		np = texte.length();
	}

	public String description() {
		return "Robot n°" + this.id + " dit : " + this.texte;
	}

	public boolean finiDeParler() {
		if (this.np == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void parle (int n) {
		for (int i = 0 ; i<n ; i++) {
			System.out.print(texte.charAt(i+this.texte.length()-this.np));
		}
		this.np-=n;
		if (this.np < 0) {
			this.np = 0;
		}
	}

	public int getId() {
		return this.id;
	}
	public int getNp() {
		return this.np;
	}
}