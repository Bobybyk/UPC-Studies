public class TabEntiersTriable implements Triable {
	private int[] tab;

	public TabEntiersTriable (int[] tab) {
		this.tab = tab;
	}

	public static void main (String[] args) {
	    System.out.println("TabEntiersTriable");
        int t[]= {16,14,65,4,3,4,8};
        TabEntiersTriable t1=new TabEntiersTriable(t);
        Triable.triBulles(t1);
        System.out.println(t1);
	}

	public void echange (int i, int j) {
		int tmp = this.tab[i];
		this.tab[i] = tab[j];
		this.tab[j] = tmp;

	}

	public boolean plusGrand (int i, int j) {
		return this.tab[i]<this.tab[j]?true:false;
	}

	public int taille() {
		return this.tab.length;
	}

	public String toString() {
		String s = "";
		for (int i = 0 ; i<this.tab.length ; i++) {
			s += this.tab[i] + ", ";
		}
		return s;
	}


}