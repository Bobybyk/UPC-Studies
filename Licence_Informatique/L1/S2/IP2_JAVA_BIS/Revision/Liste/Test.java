public class Test {
	public static void main(String[] args) {
		
		ListeDEntiers l = new ListeDEntiers();
		ListeDEntiers l2 = new ListeDEntiers();

		l.ajouteFin(1);
		l.ajouteFin(2);
		l.ajouteFin(3);
		l.ajouteFin(4);
		l.ajouteFin(5);

		l2.ajouteDebut(1);
		l2.ajouteDebut(2);
		l2.ajouteDebut(3);
		l2.ajouteDebut(4);
		l2.ajouteDebut(5);

		System.out.println("\nListe debut\n");
		l.affiche();
		System.out.println("-----------");
		l.afficheInverse();

		System.out.println("\nListe fin\n");
		l2.affiche();
		System.out.println("-----------");
		l2.afficheInverse();

		System.out.println("-----------");
		l.additionne();
		l.affiche();
	}
}