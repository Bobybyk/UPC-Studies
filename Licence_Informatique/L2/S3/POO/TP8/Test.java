import java.io.*;

public class Test {

	public static void main (String[] args) {
		
		File path = new File(args[0]);
		Noeud n = new Noeud(path);
		Arbre a = new Arbre(n);

		System.out.println("\n################## Arborescence ##################\n");
		a.afficher();

		System.out.println("\n################## Arborescence .txt ##################\n");
		a.traverser("txt");
	
		StringTransformation addBlah = (String s) -> s + ".blah";
		a.map(addBlah);
		
		System.out.println("\n################## Arborescence après transformation ##################\n");
		a.afficher();
	}

}