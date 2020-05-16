public class Palindromes {
	public static String reverse(String s) {

		int n = s.length();
		String p = "";
		char l = ' ';

		for (int i = (n-1) ; i>=0 ; i--) {
			l = s.charAt(i);
			p = p+l;
		}
		return p;

	}

    public static void main(String[] args) {
    	
        System.out.println(reverse("HelloWorld"));
    }
}
