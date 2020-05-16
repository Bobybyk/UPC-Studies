public class StringLOL {
	public static void main (String[] args) {
		System.out.println(f1("Hello World"));
	}

	public static String f1 (String s) {
		String sBis = "";
		for (int i = 0 ; i<s.length() ; i++) {
			if (!(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u' || s.charAt(i) == 'y')) {
				sBis += s.charAt(i);
			}
		}
		return sBis;
	}

}