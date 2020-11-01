import java.util.Scanner;
import java.util.LinkedList;

public class FormateurLimite extends Formateur {
	private int longeurMax;
	LinkedList<LinkedList<BoiteComposite>> doubleList;

	public FormateurLimite (String fichier, int longeurMax) {
		super(fichier);
		this.longeurMax = longeurMax;
		this.doubleList = new LinkedList<LinkedList<BoiteComposite>>();
	}

	public void read() {
		scan.useDelimiter("\n\\s*\n");
		while(scan.hasNext()) {
			LinkedList<BoiteComposite> p = readParagraphe();
			doubleList.add(p);
		}
	}

	private LinkedList<BoiteComposite> readParagraphe() {
		BoiteComposite paragraphe = new BoiteComposite();
		String para = scan.next();
		Scanner s = new Scanner(para);
		while(s.hasNext()) {
			if (paragraphe.length() < this.longeurMax) {
				paragraphe.addBoite(new BoiteMot(s.next()));
				paragraphe.addBoite(new BoiteEspace());
			} else {
				liste.add(paragraphe);
				paragraphe = new BoiteComposite();
			}
		}
		return liste;
	}

	public void print() {
		for (int i = 0 ; i < doubleList.size() ; i++) {
			printParagraphe(doubleList.get(i));
			if (i != doubleList.size()) {
				System.out.println();
			}
		}
	}

	public void printParagraphe (LinkedList<BoiteComposite> p) {
		for (int i = 0 ; i < liste.size() ; i++) {
			printLigne(liste.get(i));
			if (i != liste.size()) {
				System.out.println();
			}
		}
	}

	private void printLigne (BoiteComposite b) {
		if (!b.isEmpty()) {
			System.out.println(b);
		}
	}
}