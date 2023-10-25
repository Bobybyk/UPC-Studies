public class CommandeCat extends CommandeShell {

	public CommandeCat (Dossier racine, Dossier courant, String[] parametres) {
		super(racine, courant, parametres);
	}	

	@Override
	public Dossier executer() {
		if (super.parametre.length != 1) {
			super.erreurParam();
		}
		if (courant.getEntree(parametre[1], true).getElement() instanceof Dossier) {
			((Dossier) courant.getEntree(parametre[1], true).getElement()).afficher();
		} else {
			((FichierTexte) courant.getEntree(parametre[1], true).getElement()).afficher();
		} return super.courant;
	}

	public static void aide() {
		System.out.println("cat <name>");
	}
}