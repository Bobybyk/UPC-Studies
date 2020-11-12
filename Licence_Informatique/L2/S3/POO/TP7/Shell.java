import java.util.Scanner;
import java.io.InputStream;

public class Shell {
	private Dossier racine;
	private Dossier courant;

	public Shell (Dossier d) {
		this.racine = d;
		this.courant = d;
	}

	public void interagir (InputStream in) {
		Scanner sc = new Scanner(in);
		String[] s;
		System.out.println("-------quit : pour sortir | help : pour afficher le manuel-------");
		while (true) {
			s = decoupe(sc.nextLine());
			switch(s[0]) {
				case "mkdir": mkdir(s) ; break;
				case "cat": cat(s) ; break;
				case "ls": ls(s) ; break;
				case "cd": cd(s) ; break;
				case "ed": ed(s) ; break;
				case "mv": break;
				case "rm": break;
				case "quit": System.exit(0);
				default: CommandeShell.aide(); break;
			}
		}
	}

	private String[] decoupe (String s) {
		return s.split(" ");
	}

	private void ls(String[] s) {
		CommandeLs ls = new CommandeLs(this.courant, this.racine, s);
		ls.executer();
	}

	private void mkdir(String[] s) {
		CommandeMkdir mkdir = new CommandeMkdir(this.racine, this.courant, s);
		mkdir.executer();
	}

	private void ed(String[] s) {
		Scanner sc = new Scanner(System.in);
		CommandeEd ed = new CommandeEd(sc, true, this.racine, this.courant, s);
	}

	private void cd(String[] s) {
		CommandeCd cd = new CommandeCd(this.racine, this.courant, s);
		cd.executer();
	}

	private void cat(String[] s) {
		CommandeCd cat = new CommandeCd(this.racine, this.courant, s);
		cat.executer();
	}
}