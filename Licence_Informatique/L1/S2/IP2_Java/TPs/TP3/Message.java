public class Message {

	private final String message;
	private final Utilisateur user;

	public Message (String message, String motDePasse, Utilisateur u1) {

		if ((u1.testMotDePasse(motDePasse)) == true) {
			this.message = message;
			this.user = u1;
		} else { this.message = null; this.user = null; }
		
		System.out.println(this.message);

	}

	public Utilisateur getUser() {
		return this.user;
	}

	public String getMessage() {
		return this.message;
	}

}