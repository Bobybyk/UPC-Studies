import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;

public class Formateur {
	Scanner scan;
	LinkedList<BoiteComposite> liste;

	public Formateur (String f) {
		scan = null;
		try {
			scan = new Scanner(new File(f));
		}
		catch(Exception e) {
			System.out.println("Erreur à l'ouverture de " + f);
			e.printStackTrace();
			System.exit(1);
		}
		liste = new LinkedList<BoiteComposite>();

	}

	public static void main(String[] args) {
		Formateur f = new Formateur("texte");
		f.read();
		f.print();
		System.out.println("####################\n####################\n####################\n####################");
		FormateurLimite fL = new FormateurLimite("texte", 20);
		fL.read();
		fL.print();
		
	} 

	public void print() {
		String p = "";
		for (int i = 0 ; i < liste.size() ; i++) {
			printParagraphe(liste.get(i));
		}
	}

	public void printParagraphe (BoiteComposite b) {
		System.out.println(b);
	}

	public void read() {
		String p = "";
		while (scan.hasNext()) {
			String next = scan.nextLine();
			if (next.isBlank()) {
				if (p.equals("")) continue;
				liste.add(readParagraphe(p));
				p = "";
				continue;
			}
			p = p.equals("") ? next : (p+next+"\n");
		}
		liste.add(readParagraphe(p));
		scan.close();
	}

	private BoiteComposite readParagraphe (String p) {
		BoiteComposite pa = new BoiteComposite();
		Scanner s = new Scanner(p);
		while (s.hasNext()) {
			pa.addBoite(new BoiteMot(s.next()));
			pa.addBoite(new BoiteEspace());
		}
		s.close();
		return pa;
	}
}