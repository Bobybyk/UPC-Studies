public class CommandeCd extends CommandeShell {

	public CommandeCd (Dossier racine, Dossier courant, String[] parametres) {
		super(racine, courant, parametres);
	}

	@Override
	public Dossier executer() {
		if (super.parametre.length <= 1) {
			return super.racine;
		}
		Dossier tmp = null;

		if (acceder(super.parametre[1], true) != null) {
			if (acceder(super.parametre[1], true).getElement() instanceof Dossier) {
				tmp = (Dossier) acceder(super.parametre[1], true).getElement();
			}
		}
		return tmp;
	}

	public static void aide() {
		System.out.println("Command : cd [<foldername>]");
	}
	
}