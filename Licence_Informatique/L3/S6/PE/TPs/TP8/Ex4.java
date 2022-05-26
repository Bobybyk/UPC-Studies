import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex4 {
    
    
    static int[] tempsIn;
    static int[] tempsOut;
    static int nbrIn;
    static int nbrOut;
    static ArrayList<Integer> tps = new ArrayList<>();
    static HashMap<Integer,Integer> map = new HashMap<>();
    
    public static void main(String[] args) {
        parsing(args[0]);
        subs();
        remplissageMap();
        System.out.println(readMap());
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
                for (int i = 0 ; i < intLine.length ; i++) {
                    intLine[i] = Integer.parseInt(strLine[i]);
                }
                switch(compteur){
                    case 0:
                    nbrIn=intLine[0];
                    break;
                    case 1:
                    nbrOut=intLine[0];
                    break;
                    case 2:
                    tempsIn=new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        tempsIn[i]=intLine[i];
                    }
                    break;
                    case 3:
                    tempsOut=new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        tempsOut[i]=intLine[i];     
                    }
                    break;
                }
                compteur++;
            } 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void subs(){   
        for(int i = 0; i <nbrOut;i++){
            for(int j = 0; j <nbrIn;j++){
                if(tempsOut[i]-tempsIn[j]>0){
                    tps.add(tempsOut[i]-tempsIn[j]);
                    //System.out.println(tempsOut[i]-tempsIn[j]);
                }
            }
        }
        
    }
    
    public static int readMap(){
        int best = 0;
        int bestVal = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue()>bestVal){
                best = entry.getKey();
                bestVal = entry.getValue();
                
            }
            if(entry.getValue()==bestVal && entry.getKey()<best){
                best = entry.getKey();
                bestVal = entry.getValue();
            }
           
        }
        if(best==Integer.MAX_VALUE){
            return 0;
        }
        return best;
    }
    
    public static void remplissageMap(){
        int tmp=0;
        for(int i=0;i<tps.size();i++){
            if(map.containsKey(tps.get(i))){
                tmp=map.get(tps.get(i))+1;
                map.put(tps.get(i), tmp);
            }
            else{
                map.put(tps.get(i), 1);
            }
        }
        /*for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" valeur: "+ entry.getValue());
        }*/
        
    }
    
    
    
    
    
    
    
    
}




