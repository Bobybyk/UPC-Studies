public class TriBinaire implements Triable {
	private String[] tabBit;

	public TriBinaire (String[] t) {
		this.tabBit = t;	// On considère que t est un tabBitleau d'éléments binaires
	}

	public static void main (String[] args) {
	    System.out.println("TabBinaire");
        String t[]= {"01","110","00110","000","01010","01101","11111"};
        TriBinaire t1=new TriBinaire(t);
        Triable.triBulles(t1);
        System.out.println(t1);
	}

	public void echange (int i, int j) {
		String tmp = this.tabBit[i];
		this.tabBit[i] = tabBit[j];
		this.tabBit[j] = tmp;

	}

	public boolean plusGrand (int i, int j) {
		int a = Integer.parseInt(this.tabBit[i], 2);
		int b = Integer.parseInt(this.tabBit[j], 2);
		return a<b?true:false;
	}

	public int taille() {
		return this.tabBit.length;
	}

	public String toString() {
		String s = "";
		for (int i = 0 ; i<this.tabBit.length ; i++) {
			s += this.tabBit[i] + ", ";
		}
		return s;
	}

}