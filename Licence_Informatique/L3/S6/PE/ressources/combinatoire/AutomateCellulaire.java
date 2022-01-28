import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class AutomateCellulaire {

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.err.println("Entrer longueur de ligne souhaitée");
			System.exit(1);
		}

		// taille de l'automate
		int lineLength = Integer.parseInt(args[0]);

		// pour fichier output
    	String fileName = "nbr_gen_par_cycle_ligne_" + lineLength + ".txt";
    	String encoding = "UTF-8";

    	// nombre total de combinaisons à tester, soit 2^lineLength
        int rows = (int) Math.pow(2,lineLength);

        // tableau de l'automate courant
		int[] tab = new int[lineLength];

		// stockage des états déjà rencontrés pour une combinaison donnée
		ArrayList<int[]> evaluatedConf = new ArrayList<int[]>();

		// compteurs, type long nécessaire pour lineLength >= 28 
		long combinaisons = 0;
		long moyenne = 0;

		try {
			// création ou ouverture du fichier d'output
			PrintWriter writer = new PrintWriter(fileName, encoding);
	        // pour i allant de 0 à 2^lineLength
	        for (int i=0; i<rows; i++) {
				combinaisons += 1;
				// création de la table de vérité courante
	            for (int j=lineLength-1; j>=0; j--) {
	                tab[j] = ((i/(int) Math.pow(2, j))%2);
	            }
	            // ajout de la table à la liste de celles déjà testées
				evaluatedConf.add(tab);
				int generations = 0;
				while(true) {
					print(tab);
					tab = evolve(tab);
					// vérification de l'existance de l'état d'évolution
					if (confExist(tab, evaluatedConf)) {
						/* 
						 * commenter ligne 41 & 42 si argument >= 15
						 * pour alléger la quantité d'opérations (appels système)
						*/ 
						//writer.println(generations+1);
						System.out.println("Number of gen : " + (generations+1));
						moyenne += (generations+1);
						break;
					}
					evaluatedConf.add(tab);	
					generations+=1;
				}
				tab = new int[lineLength];
				evaluatedConf = new ArrayList<int[]>();
				generations = 0;
	        }
	        moyenne = moyenne/combinaisons;
			System.out.println("Longueur de la ligne : " + lineLength);
			System.out.println("total combinaisons : " + combinaisons);
			System.out.println("moyenne nombre de generations par cycle : " + moyenne);
			writer.println("Longueur de la ligne : " + lineLength);
			writer.println("total combinaisons : " + combinaisons);
			writer.println("moyenne nombre de generations par cycle : " + moyenne);
	        writer.close();
	    } catch (IOException e) {
	    	System.out.println("Erreur lors de l'écriture fichier");
	    	e.printStackTrace();
	    }
	}

	static int[] reset(int[] t) {
		for(int i=0; i<t.length;i++) {
			t[i] = 0;
		}	
		return t;
	}

	static boolean confExist(int[] conf, ArrayList listToCheck) {
		for (int i = 0 ; i<listToCheck.size() ; i++) {
			if(Arrays.equals((int[])conf, (int[])listToCheck.get(i))) {
				return true;
			}
		}
		return false;
	}

	static int[] evolve(int[] t) {
		int[] t2 = new int[t.length];
		for(int i=0;i<t.length;i++) {
			if(i == 0) {
				if(t[i] == 0 && (t[i+1] == 1 && t[t.length-1] == 0))
					t2[i] = 1;
				else if(t[i] == 0 && (t[i+1] == 0 && t[t.length-1] == 1))
					t2[i] = 1;
				else if(t[i] == 1 && (t[i+1] == 1 && t[t.length-1] == 0))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 0 && t[t.length-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 1 && t[t.length-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 0 && t[t.length-1] == 0))
					t2[i] = 1;
			} else if(i == t.length-1) {
				if(t[i] == 0 && (t[0] == 1 && t[i-1] == 0))
					t2[i] = 1;
				else if(t[i] == 0 && (t[0] == 0 && t[i-1] == 1))
					t2[i] = 1;
				else if(t[i] == 1 && (t[0] == 1 && t[i-1] == 0))
					t2[i] = 0;
				else if(t[i] == 1 && (t[0] == 0 && t[i-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[0] == 1 && t[i-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[0] == 0 && t[i-1] == 0))
					t2[i] = 1;
			} else {
				if(t[i] == 0 && (t[i+1] == 1 && t[i-1] == 0))
					t2[i] = 1;
				else if(t[i] == 0 && (t[i+1] == 0 && t[i-1] == 1))
					t2[i] = 1;
				else if(t[i] == 1 && (t[i+1] == 1 && t[i-1] == 0))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 0 && t[i-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 1 && t[i-1] == 1))
					t2[i] = 0;
				else if(t[i] == 1 && (t[i+1] == 0 && t[i-1] == 0))
					t2[i] = 1;
			}			
		}
		return t2;
	}



	static void print(int[] t) {
		for(int i=0; i<t.length;i++) {
			switch(t[i]) {
				case 0:
					System.out.print("[ ]");
					break;
				case 1:
					System.out.print("[X]");
					break;
				default:
					break;
			}
		}	
		System.out.println("\n");
	}
}
