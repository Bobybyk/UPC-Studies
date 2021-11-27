public class Dictionnaire implements Triable {
	private String[] tabS;

	public Dictionnaire (String[] t) {
		this.tabS = t;
	}

	public static void main (String[] args) {
	    System.out.println("Dictionnaire");
        String t[]= {"hello","world","aa", "comment","bonjour", "a"};
        Dictionnaire t1=new Dictionnaire(t);
        Triable.triBulles(t1);
        System.out.println(t1);
	}

	public void echange (int i, int j) {
		String tmp = this.tabS[i];
		this.tabS[i] = tabS[j];
		this.tabS[j] = tmp;

	}

	public boolean plusGrand (int i, int j) {
		String chI = this.tabS[i];
		String chJ = this.tabS[j];
		int chIntI = 0;
		int chIntJ = 0;;
		for (int a = 0 ; a<chI.length() ; a++) {
			chIntI += chI.charAt(a) - 'a' + 1;
		}
		for (int b = 0 ; b<chJ.length() ; b++) {
			chIntJ += chJ.charAt(b) - 'a' + 1;
		}
		boolean verif = chIntJ<chIntJ?true:false;
		return verif;
	}

	public int taille() {
		return this.tabS.length;
	}

	public String toString() {
		String s = "";
		for (int i = 0 ; i<this.tabS.length ; i++) {
			s += this.tabS[i] + ", ";
		}
		return s;
	}

}