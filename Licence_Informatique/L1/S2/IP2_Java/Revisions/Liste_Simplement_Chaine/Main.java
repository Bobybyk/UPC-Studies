public class Main {
	
	public static void main (String[] args) {
		ListeEntiers a = new ListeEntiers();
		
		a.add(5);
		a.afficher();
		a.add(8);
		a.afficher();
		a.add(9);
		a.afficher();
		a.add(3);
		a.afficher();

		a.additionner();
		a.afficher();

		System.out.println(a.longueur());
	}
}