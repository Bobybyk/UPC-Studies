public class Test {
	
	public static void main (String[] args) {
		System.out.println("--------------Exercice 1--------------");

		Utilisateur u1 = new Utilisateur ("SirHenryAllen", "123", "matt.lefranc@gmail.com");
		System.out.println("Pseudo : " + u1.getPseudonyme());
		u1.changerMotDePasse("1234", "123");
		u1.changerMotDePasse("123", "1234");


		System.out.println("\n--------------Exercice 2--------------");

		Utilisateur u2 = new Utilisateur ("Pouêt", "456", "pouêt.pouêt@yahoo.fr");
		Utilisateur u3 = new Utilisateur ("Maltazard", "789", "malt.azard@outlook.com");
		
		Message m1 = new Message (u1, "HelloWorld");
		Message m2 = new Message (u2, "Introduction à la programmation Java");
		Message m3 = new Message (u3, "Semaine du 10 février");


		System.out.println("\n--------------Exercice 3--------------");

		Salon s = new Salon();

		s.ajouterUtilisateur(u1);
		s.ajouterUtilisateur(u2);
		s.ajouterUtilisateur(u3);

		s.ajouterMessage(m1);
		s.ajouterMessage(m2);
		s.ajouterMessage(m3);

		s.afficher();

		s.exclusUtilisateur(u2);

		s.afficher();


		System.out.println("\n--------------Exercice 4--------------");

		Chat c = new Chat();

		c.ajouterSalon(s);

		System.out.println("Utilisateur présent : " + c.estPresent(u2));
		System.out.println("Utilisateur présent : " + c.estPresent(u3));

	} 
}