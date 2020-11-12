import java.util.*;

public class Test {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Missing <file> parameter");
			System.exit(1);
		}

		Dossier racine = new Dossier();
		Shell s = new Shell(racine);

		try {
			s.interagir(System.in);
		}
		catch (Exception e) {
			System.out.println("Erreur lors de l'ouverture du fichier : ");
			e.printStackTrace();
			System.exit(1);
		}
	}

}