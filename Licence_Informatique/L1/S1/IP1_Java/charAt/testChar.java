public class testChar {

	public static int chara (String a) {
		int x = 0;
		char b = a.charAt(0);
		for (int i = 0 ; i < a.length(); i++) {
			b = a.charAt(i);
			x = x+1;

		}

		return x;
	}
	public static void main (String[] args) {
		System.out.println(chara("HelloWorldlolololstrophelloworldsvp"));
	}

}