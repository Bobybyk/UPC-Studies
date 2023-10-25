public class Ingredient {
	private String nom;
	private int stockC;
	private int stockA;

	public Ingredient(String n) {
		this.nom = n;
		this.stockC = null;
		this.stockA = null;
	}

	public void listeDeCourse (int surplus) {
		if (this.stockC < this.stockA) {
			int listeCourse = this.stockA - this.stockC + surplus;
			System.out.println("Acheter " + listeCourse + " nom");
		}
	}

	public void achat (int quantite) {
		this.stockC += quantite;
	}

	public void consomme() {
		if (this.stockC >= 0) {
			this.stockC --;
		} else {
			System.out.println("Stock Vide");
		}
	}
}