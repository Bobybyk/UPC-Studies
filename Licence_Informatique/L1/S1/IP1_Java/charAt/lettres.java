public class lettres {
	
	public static int charrr (String a) {
	int x = 0;
	char b = a.charAt(0);
	for (int i = 0; i < a.length(); i++) {
		b = a.charAt(i);
		x = x + 1;
		System.out.print(b);
		System.out.println(x);
	}
	return x;
	
}

	public static void main (String[] args) {
	
		System.out.println(charrr("HelloWord"));

}

}