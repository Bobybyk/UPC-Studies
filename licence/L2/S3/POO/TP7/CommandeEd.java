import java.util.Scanner;

public class CommandeEd extends CommandeShell {
	Scanner sc;
	boolean echo;

	public CommandeEd (Scanner sc, boolean echo, Dossier racine, Dossier courant, String[] parametres) {
		super(courant, racine, parametres);
		this.sc = sc;
		this.echo = echo;
		if (parametres.length > 2 || parametres.length < 1) {
			super.erreurParam();	
			this.executer();
		}
	}

	@Override
	public Dossier executer() {
		if (super.courant.getEntree(super.parametre[1], true) != null) {
			if (super.courant.getEntree(super.parametre[1], true).getElement() instanceof FichierTexte) {
				((FichierTexte) super.courant.getEntree(super.parametre[1], true).getElement()).editer(this.sc, this.echo);
			}
		}
		return super.courant;
	}

	public static void aide() {
		System.out.println("ed <filename>");
	}	
}