public class Palindrome {
	
	public static void main (String[] args) {
		System.out.println(isPalindrome("olla"));
	}

	public static boolean isPalindrome(String chaine) {
		String tmp = "";
		for (int i = chaine.length() ; i>0 ; i--) {
			tmp += chaine.charAt(i-1);
		}
		if (chaine.equals(tmp)) {
			return true;
		} return false;
	}
}