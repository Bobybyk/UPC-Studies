import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class exo3{

    static int ritaBougie;
    static int theoBougie;
    static int diffAge;


    static int ritaAge = 4;
    static int theoAge = 3;

    //txt file parser 
    //first line is diffAge second is ritaAge and third is TheoAge
    
    public static void Parser(String fileName){
        try{
            Path fN = Path.of(fileName);
            String content = Files.readString(fN);
            //System.out.println(content);
            String[] lines = content.split("\n");
            diffAge = Integer.parseInt(lines[0]);
            ritaBougie = Integer.parseInt(lines[1]);
            theoBougie = Integer.parseInt(lines[2]);
        } 
        catch(IOException e){
            System.out.println("Error: " + e);
        }
    }


    public static void main(String[] args) {
        Parser(args[0]);
	/*
        System.out.println("DiffAge: " + diffAge);
        System.out.println("TheoBougie: " + theoBougie);
        System.out.println("ritaBougie: " + ritaBougie);
	*/

        int nbBougie = 4;
        while(ritaBougie>=0 && (ritaBougie-nbBougie)>=0){
            ritaAge++;
            ritaBougie=ritaBougie-nbBougie;
            nbBougie++;
        }
      //      System.out.println("RitaAge: " + ritaAge);
        
        nbBougie = 3;
        while(theoBougie>=0 && (theoBougie-nbBougie)>=0){
            theoAge++;
            theoBougie=theoBougie-nbBougie;
            nbBougie++;
        }
       // System.out.println("theoAge: " + theoAge);

        System.out.println(ritaBougie);
    }
}
