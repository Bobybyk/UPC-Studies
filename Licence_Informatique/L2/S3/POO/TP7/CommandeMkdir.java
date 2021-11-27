public class CommandeMkdir extends CommandeShell {
	
	public CommandeMkdir (Dossier racine, Dossier courant, String[] parametres) {
		super(racine, courant, parametres);
	}

	@Override
	public Dossier executer() {
		if (super.parametre.length != 2 || super.courant.getEntree(parametre[1], false) != null) {
			this.aide();
			return super.courant;
		}
		super.courant.getListe().add(new Entree(super.courant, super.parametre[1]));
		return super.courant;
	}	

	public static void aide() {
		System.out.println("mkdir [<foldername>]");
	}
}