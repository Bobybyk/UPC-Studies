import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Test{

    static float frequences[];
    static int notes[];
    static int nombreNotes = 0;
    static int longueurInit = 0;
    static int preSequence[];
    static int si[];
    static int nbrJouees=0;
    static int lastNote;

    //Initialise le tableau des fréquences 
    private static void calculFrequences() {
        int moyenne = somme(notes);
        System.out.println("Moyenne des notes : " + moyenne);
        System.out.print("liste des fréquences : ");
        for(int i=1;i<frequences.length; i++){
            frequences[i] =(float) notes[i] / moyenne;
            System.out.print(frequences[i]+" ");
        }
        System.out.println();
    }

    public static int somme(int[] toto){
        int tmp=0;
        for(int i=0;i<toto.length;i++){
            tmp+=toto[i];
        }
        return tmp;
    }

    // A-t-on déjà joué toute la séquence possible ?
    private static boolean stop() {

        return notes[lastNote]-1 < si[lastNote] && notes[lastNote]+1 < si[lastNote];

}
    public static boolean isOk(){
        for(int i=0;i<notes.length;i++){
            if(!isJouable(i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        parsing(args[0]);
        calculFrequences();
        print("Liste occurence par note : ", si);
        lastNote = preSequence[preSequence.length-1];
        while(true){
            int bestNote = -1;
            float bestGauche = Integer.MAX_VALUE;

            if(stop()){
                System.out.println("INFINI");
                nbrJouees-=preSequence.length;
                break;
            }

            for(int i=1; i<nombreNotes+1;i++){    
                if(isJouable(i)){
                    if(bestNote == -1) {
                        bestNote = i;   
                    }
                    if(distanceGauche(i)<bestGauche) {
                        bestNote = i;
                        bestGauche = distanceGauche(i);
                    }
                }
            }
            /*if(bestNote==-1) { 
                System.out.println("BLOQUE");
                break;
            }*/if(!isOk()){
                System.out.println("BLOBLO");
                break;
            }
            else{ 
                System.out.println("Note jouee : " + notes[bestNote]);
                 //JOUER LA NOTE
                si[bestNote]++;
                nbrJouees++;
                lastNote = bestNote;
            }
            
        }
        print("Liste occurence par note (si) : ", si);
        System.out.println("Dernière note jouée (ai) : " + notes[lastNote]);
    }

    public static void print(String type, int[] toto){
        System.out.print(type + " : ");
        for(int i=1;i<toto.length;i++){
            System.out.print(toto[i] + " ");
        }
        System.out.println("");
    }

    public static boolean isJouable(int index){
        return(si[index]<(frequences[index]*nbrJouees)+1 && si[index]>(frequences[index]*nbrJouees)-1);
    }

    public static float distanceGauche(int index){
        return(si[index]-frequences[index]*nbrJouees-1);
    }

    public static float[] decalage(int index){
        float[] decale=new float[2];
        decale[0]=(nbrJouees*frequences[index])-1;
        decale[1]=(nbrJouees*frequences[index])+1;
        return decale;
    }
    
    private static void parsing(String path) {
		// ouverture du fichier passé en argument
		File f = new File(path);
        int compteur=0;
		// lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] strLine = sc.nextLine().split(" ");
                int[] intLine = new int[strLine.length];
                try {
                    for (int i = 0 ; i < intLine.length ; i++) {
                        intLine[i] = Integer.parseInt(strLine[i]);
                    }
                    if(compteur==0){
                        nombreNotes=intLine[0];
                        longueurInit=intLine[1];
                        //System.out.print(nombreNotes+" "+longueurInit);
                        //System.out.println(" ");
                    }
                    else if(compteur==1){
                        notes=new int[nombreNotes+1];
                        si=new int[nombreNotes+1];
                        frequences = new float[nombreNotes+1];
                        for(int i=1;i<notes.length;i++){
                            notes[i]=intLine[i-1];
                            //System.out.print(notes[i]+" ");
                        }
                        //System.out.println("");
            
                    }
                    else{
                        preSequence=new int[intLine.length];
                        nbrJouees=preSequence.length;
                        for(int i=0;i<intLine.length;i++){
                            preSequence[i]=intLine[i];
                            //System.out.print(preSequence[i]+" ");
                        }
                        //System.out.println("");
                        initSi();
                    }
                    compteur++;
                } catch (NumberFormatException e) {
                    System.out.println("Mauvais format de donnees pour : " + path);
                    System.exit(1);
                } 

            } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
	}

    public static void initSi(){
        for(int i=0;i<preSequence.length;i++){
            si[preSequence[i]]++;
        }
    }

    // écriture du résultat dans un fichier res.out
    private static void writeOutput(int res) {
        try {
            System.out.println("Debut ecriture resultat");
            File outputfile = new File("res.out");
            outputfile.createNewFile();

            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(res);
            bw.close();
            System.out.println("Fin ecriture resultat");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}