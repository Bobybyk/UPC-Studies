public class CommandeLs extends CommandeShell {

	public CommandeLs (Dossier racine, Dossier courant, String[] parametres) {
		super(racine, courant, parametres);
	}	

	@Override
	public Dossier executer() {
		if (super.parametre.length == 1) {
			if (super.courant.getListe() != null) {
				for (int i = 0 ; i<super.courant.getListe().size() ; i++) {
					System.out.println(super.courant.getListe().get(i).getNom());
				}
			}
		}
		else if (super.parametre.length == 2) {
			// Lister les éléments du répertoire placer en argument
		}
		return super.courant;
	}
}