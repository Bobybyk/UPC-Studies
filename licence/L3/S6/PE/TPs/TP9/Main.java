import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {


    static int nbrCouleur;
    static int valMax;
    static int tailleMax;
    static int nbrDominos;
    static HashMap<Integer,int[]> dominosListAbs = new HashMap<>();
    LinkedList<ArrayList<int[]>> results = new LinkedList<>();
    
    private static ArrayList<int[]> createConfig() {

        int arrayListPos = 0;
        int tabPos = 0;
        ArrayList<int[]> dominosGrid = new ArrayList<>();

        HashMap<Integer,int[]> dominosList = dominosListAbs;
        LinkedList<Integer> dominosToRemove = new LinkedList<Integer>();

        // Tant qu'il y a des dominos à placer, on continue d'évaluer
        while (!dominosGrid.isEmpty()) {

            for (Entry<Integer, int[]> d : dominosList.entrySet()) {

                // On évalue le cas où on se place sur la première ligne à la première case
                if (tabPos == 0 && arrayListPos == 0) {
                    System.out.println("premier domino placé");
                    int[] tab = new int[tailleMax];
                    dominosGrid.add(tab);
                    dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                    dominosToRemove.add(d.getKey());
                    tabPos++;
                    continue;
                }
    
                // id du dominos à la position courante
                int index = dominosGrid.get(arrayListPos)[tabPos-1];
    
                // On évalue les cas où on se place sur la première ligne et pas à la première case
                if (tabPos != 0 && arrayListPos == 0) {
                    // si la droite du dominos précédent est compatible avec la gauche du dominos courant
                    if (d.getValue()[4] == dominosGrid.get(index)[2]) {
                        System.out.println("domino placé sur la première ligne à la case : " + tabPos);
                        dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                        dominosToRemove.add(d.getKey());
                        tabPos++;
                        if (tabPos >= tailleMax) {
                            tabPos = 0;
                            arrayListPos++;
                        }
                        continue;
                    }
                }
    
                // On évalue les cas où on ne se place pas sur la première ligne et où on est sur la première case
                if (tabPos == 0 && arrayListPos > 0) {
                    // si le bas du domino d'au-dessus est compatible avec le haut du domino courant
                    if (d.getValue()[1] == dominosGrid.get(index)[3]) {
                        System.out.println("domino placé sur la première case de la ligne : " + arrayListPos);
                        dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                        dominosToRemove.add(d.getKey());
                        tabPos++;
                        continue;
                    }
                }
    
                /* On évalue les cas où on ne se place ni sur la première ligne ni sur la première case
                 * --------
                 * Donc si la droite du dominos précédent est compatible avec la gauche du dominos courant
                 * et que la partie haute du dominos précédent est compatible avec la partie basse du dominos courant
                 */
                if (d.getValue()[1] == dominosGrid.get(index)[3] && d.getValue()[4] == dominosGrid.get(index)[2]) {
                    System.out.println("domino placé sur la case : " + tabPos + " de la ligne : " + arrayListPos);
                    dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                    dominosToRemove.add(d.getKey());
                    tabPos++;
                    if (tabPos >= tailleMax) {
                        tabPos = 0;
                        arrayListPos++;
                    }
                    continue;
                }
            }

            // suppression des dominos déjà ajoutés
            for(Integer d : dominosToRemove) {
                dominosList.remove(d);
            }

        }

        return dominosGrid;

    }

    private static void testConfig() {

    }

    public static void parsing(String path) {
        // ouverture du fichier passé en argument
        File f = new File(path);
        int compteur=0;
        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] strLine = sc.nextLine().split(" ");
                int[] intLine = new int[strLine.length];
                for (int i = 0 ; i < intLine.length ; i++) {
                    intLine[i] = Integer.parseInt(strLine[i]);
                }
                if(compteur==0){
                    nbrCouleur=intLine[0];
                    valMax=intLine[1];
                    tailleMax=intLine[2];
                    nbrDominos=intLine[3];
                }
                else{
                    int[] dominos = new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        dominos[i]=intLine[i];
                    }
                    dominosListAbs.put(compteur-1, dominos);
                    System.out.println(dominos);
                }
                compteur++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeOutput(ArrayList<int[]> resDominos) {
        try {
            System.out.println("Debut ecriture resultat");
            File outputfile = new File("res.out");
            outputfile.createNewFile();

            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            PrintWriter bw = new PrintWriter(fw);
            
            for (int j = 0 ; j < tailleMax ; j++) {
                for (int i = 0 ; i < resDominos.size() ; i++) {
                    bw.print(resDominos.get(i) + " ");
                }
            }

            bw.close();
            System.out.println("Fin ecriture resultat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        parsing(args[0]);
        writeOutput(createConfig());

    }
}
