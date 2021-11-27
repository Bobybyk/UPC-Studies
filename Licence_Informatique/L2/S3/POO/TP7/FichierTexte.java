import java.util.Scanner;

public class FichierTexte extends Element implements Affichable, Editable {
	private String contenu;

	public FichierTexte(String c) {
		this.contenu = c;
	}

	public String getType() {
		return "texte";
	}

	public void afficher() {
		System.out.println(this.contenu);
	}

	public void editer(Scanner sc, boolean echo) {
		String com ="";
		while (!(com.equals("."))) {
			com = sc.nextLine();
			if (echo) {
				System.out.println(com);
			}
		} 
	}

}
