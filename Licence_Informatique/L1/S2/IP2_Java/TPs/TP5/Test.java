public class Test {
	
	public static void main (String[] args) {

		ListeDEntiers a = new ListeDEntiers ();

		a.add(6);
		a.add(3);
		a.add(6);

		System.out.println("\nListe d'entiers a : " + a.description());
		System.out.println("Taille de la liste a : " + a.taille());
		System.out.println("Somme de la liste d'entiers a : " + a.somme());

		a.ajouter_en_i(20, 4);
		System.out.println("Liste d'entiers a après ajout : " + a.description() + "\n");

		//------------------------------
		
		ListeDEntiers b = new ListeDEntiers ();

		b.add(7);
		b.add(4);
		b.add(7);

		System.out.println("\nListe d'entiers b : " + b.description());
		System.out.println("Taille de la liste b : " + b.taille());
		System.out.println("Somme de la liste d'entiers b : " + b.somme());

		b.ajouter_en_i(20, 4);
		System.out.println("Liste d'entiers b après ajout : " + b.description() + "\n");		

		//------------------------------

		System.out.println("Liste a = liste b : " + b.egal(a) + "\n");
	}
}