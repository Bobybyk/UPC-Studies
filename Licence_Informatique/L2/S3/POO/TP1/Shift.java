public class Shift {

	public static void main(String[] args) {
		String s = "";
		for (int i = 0 ; i<args.length ; i++) {
			s += args[i] + " ";
		}
		String a = "";
		for (int i = 0 ; i < s.length() ; i++) {
			if (checkVoy(s.charAt(i)) != 0) {
				a += checkVoy(s.charAt(i));
			}
			else {
				a += s.charAt(i);
			}
		}
		System.out.println(a);
	}

	public static char checkVoy (char a) {
		char[] voy = {'a', 'e', 'i', 'o', 'u', 'y'};
		char[] majVoy = {'A', 'E', 'I', 'O', 'U', 'Y'};

		for (int i = 0 ; i < voy.length ; i++) {
			if (a == voy[i]) {
				return voy[(i+1)%6];
			}
			if (a == majVoy[i]) {
				return majVoy[(i+1)%6];
			}
		}
		return 0;
	}

}