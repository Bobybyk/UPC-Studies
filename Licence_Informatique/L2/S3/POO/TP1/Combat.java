public class Combat {

	public static void main (String[] args) {

		Informations i1 = new Informations(8, 5, 6);
		Informations i2 = new Informations(6, 12, 4);

		Personnage p1 = new Personnage (i1, "Paul");
		Personnage p2 = new Personnage (i2, "Jerome");

		System.out.println(p1.toString());
		System.out.println(p2.toString());

		System.out.println("#########");

		p1.lutte(p2);
	}
}