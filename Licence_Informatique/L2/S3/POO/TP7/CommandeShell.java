import java.util.Scanner;

public abstract class CommandeShell {
	Dossier racine, courant;
	String[] parametre;
	public abstract Dossier executer();

	public CommandeShell (Dossier r, Dossier c, String[] p) {
		this.racine = r;
		this.courant = c;
		this.parametre = p.clone();
	}

	public static void aide() {
		System.out.println("------HELP------");
		System.out.println("cat <name>");
		System.out.println("cd [<foldername>]");
		System.out.println("cd <src> <dst>");
		System.out.println("ed <filename>");
		System.out.println("ls [<name>]");
		System.out.println("mkdir [<foldername>]");
		System.out.println("mv <src> <dst>");
		System.out.println("rm <name>");
		System.out.println("----------------");
	}

	protected static void erreurParam() {
		System.out.println("Pas un bon nombre de paramètres.");
		aide();
	}

	protected Entree acceder (String chemin, boolean creer) {
		Scanner scan = new Scanner(chemin).useDelimiter("/");
		String value = "";
		Dossier dos = chemin.startsWith("/")?this.racine:this.courant;

		while(scan.hasNext()) {
			value = scan.nextLine();
			if (dos.getEntree(value, creer) == null) {
				return null;
			}
			if (dos.getEntree(value, creer).getElement().getType().equals("Dossier")) {
				dos = (Dossier) dos.getEntree(value, creer).getElement();
			}
		}
		return dos.getParent().getEntree(value, creer);
	}

}