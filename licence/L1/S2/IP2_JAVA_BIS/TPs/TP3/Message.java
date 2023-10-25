public class Message {
	private Utilisateur u;
	private final String message;

	public Message (Utilisateur u, String m) {
		this.u = u;
		this.message = m;
	}

	public Utilisateur getUtilisateur() {
		return this.u;
	}
	public String getMessage() {
		return this.message;
	}

}