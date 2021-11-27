public class Test {
	
	public static void main (String[] args) {

		Utilisateur u1 = new Utilisateur ("Test", "Test123", "Test@Test.com");
		u1.changerMotDePasse("Test123", "Test123");

		Message m1 = new Message ("Hello World", "Test123", u1);

		Salon s1 = new Salon ();
		s1.ajouterUtilisateur(u1);
		s1.estPresent(u1);

		Utilisateur u2 = new Utilisateur ("Test2", "Test456", "Test2@Test2.com");
		u1.changerMotDePasse("Test456", "Test654");

		Message m2 = new Message ("Hello World u2", "Test456", u2);

		Salon s2 = new Salon ();
		s2.ajouterUtilisateur(u2);
		s2.estPresent(u2);

		s1.ajouterMessage(m1);

	}

}