public class CelluleChar {
	private char c;
	private CelluleChar next;

	public CelluleChar (char c, CelluleChar n) {
		this.c = c;
		this.next = n;
	}

	public static void compare(CelluleChar c1, CelluleChar c2) {
		while (c1.next != null && c2.next != null) {
			if (c1.c != c2.c) {
				System.out.println("cellules différentes");
				return;
			}
		}
		System.out.println("cellules égales");
	}
}