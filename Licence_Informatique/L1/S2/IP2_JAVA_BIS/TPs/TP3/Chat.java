public class Chat {
	private Salon[] salon;

	public Chat() {
		this.salon = null;
	}

	public void ajouterSalon(Salon s) {
		Salon[] sal;
		if (this.salon == null) {
			sal = new Salon[1];
			sal[0] = s;
		} else {
			sal = new Salon[this.salon.length + 1];
			for (int i = 0 ; i<this.salon.length ; i++) {
				sal[i] = this.salon[i];
			}
			sal[sal.length-1] = s;
		}
		this.salon = sal;
	} 

	public boolean estPresent (Utilisateur u) {
		for (int i = 0 ; i<this.salon.length ; i++) {
			if (this.salon[i].estPresent(u)) {
				return true;
			}
		} return false;
	}

}