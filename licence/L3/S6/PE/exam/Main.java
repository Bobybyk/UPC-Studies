import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    // grille des mots
    static char[][] grid = new char[500][500];

    // Liste de toutes les lignes du fichier
	private static LinkedList<String> ficLines = new LinkedList<>();

    // tableau pour stocker chaque mot de la ligne courante
	private static String[] lineSplited;

    // liste mots et points
    private static HashMap<String, Integer> wordAndPointsList = new HashMap<>();

    // liste des mots et leur indice
    private static HashMap<Integer, String> listMots = new HashMap<>();
    private static LinkedList<Integer> longestWordsIndexList;

    // liste mots placés
    private static LinkedList<String> listeMotsPlaces = new LinkedList<>();

    private static int nbrMots;

    public static PrintWriter fileOut;

    /*
    * PARSING
    */
    private static boolean parsing(String path) {
        // ouverture du fichier passé en argument
		File f = new File(path);

		// lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
		try {
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNextLine()) {
					ficLines.add(sc.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            return false;
		}

        for (int i = 0 ; i < ficLines.size() ; i++) {
            
            if (i == 0) {
                nbrMots = Integer.parseInt(ficLines.get(i));
            } else {
                lineSplited = split_on_char(ficLines.get(i));
                wordAndPointsList.put(lineSplited[0], Integer.parseInt(lineSplited[1]));
                listMots.put(i-1, lineSplited[0]);
            }
        }

        return true;
    }

    /*
     * Converti la chaine de caractère en tableau
     */ 
	public static String[] split_on_char(String line) {
		return line.split(" ");
	}

    private static boolean writeOutput() {
        try {
            
            PrintWriter pw = new PrintWriter(new File("res.out"));

            pw.println(listeMotsPlaces.size());

            for (int i = 0 ; i < listeMotsPlaces.size() ; i++) {
                pw.println(listeMotsPlaces.get(i));
            }

            

            pw.close();

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        
    } 

    public static void main(String[] args) {
        parsing(args[0]);
        getLongestWord();
        algoNaif();
        writeOutput();
    }

    public static String testPlaceWord(int index, int x, int y, int direction) {


        // horizontal
        if (direction == 0) {
            for (int i = x+2 ; Character.isLetter(grid[i][y]) ; i++) {
                
                System.out.println("avance horizontale");

                if (grid[i][y] == listMots.get(index).charAt(0)) {
                    System.out.println("emplacement trouvé parcours horizontal");
                    System.out.println(i+" "+y);
                    return index + " " + i + " " + y;
                }
            }
            return null;
        }

        // vertical
        if (direction == 1) {
            for (int i = y+2 ; Character.isLetter(grid[x][i]) ; i++) {
                
                System.out.println("avance vertical");

                if (grid[x][i] == listMots.get(index).charAt(0)) {
                    System.out.println("emplacement trouvé parcours vertical");
                    return index + " " + x + " " + i;
                }
            }
            return null;
        }

        return null;

    }

    public static void writeWordOnGrid(int index, int direction, int x, int y) {
        String word = listMots.get(index);
        for(int i=0;i<word.length();i++) {
            if(direction == 0) {        //0 c'est horizontal
                grid[x+i][y] = word.charAt(i);
            } else {    //1 c'est vertical
                grid[x][y+i] = word.charAt(i);
            }
        }
    }

    static void addWordToList(int index, int direction, int x, int y) {
        String instr = index+" "+x+" "+y+" ";
        if(direction == 0)
            instr += "H";
        else 
            instr += "V";
        listeMotsPlaces.add(instr);
    }

    public static void algoNaif() {
        int first = longestWordsIndexList.get(0);
        writeWordOnGrid(first, 0, 0, 0);    //On place le premier dans la grille horizontalement
        addWordToList(first, 0, 0, 0);
        listMots.remove(first);
        longestWordsIndexList.remove(0);

        int direction = 0;
        int x = 0;
        int y = 0;
        int i = 0;
        
        while(!longestWordsIndexList.isEmpty()) {
            
            int index = longestWordsIndexList.get(i);

            if (index >= listMots.size()) {
                break;
            }

            String res = testPlaceWord(index, x, y, direction);

            if(res == null) {
                i++;
            } else {
                direction = (direction == 0) ? 1 : 0;

                x = Integer.parseInt(res.split(" ")[1]);
                y = Integer.parseInt(res.split(" ")[2]);
                writeWordOnGrid(index, direction, x, y);
                addWordToList(index, direction, x, y);
                listMots.remove(index);
                longestWordsIndexList.remove(i);
                i = 0;
            }



        }
    }

    public static void getLongestWord() {
        longestWordsIndexList = new LinkedList<Integer>();
        int maxLength = 0;
        for(Integer i : listMots.keySet()) {
            if(listMots.get(i).length() > maxLength)
                maxLength = listMots.get(i).length();
        }

        while(maxLength > 1) {
            for(Integer i : listMots.keySet()) {
                if(listMots.get(i).length() == maxLength)
                    longestWordsIndexList.add(i);
            }
                maxLength--;
        }
    }
}