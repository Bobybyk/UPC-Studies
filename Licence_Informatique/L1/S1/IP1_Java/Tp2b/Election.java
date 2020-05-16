/* Pour pouvoir générer des nombres aléatoires */
import java.util.Random;

public class Election {


    public static void proportionnelle (int v1, int v2, int v3) {



    }

    /***********************************/
    /* Ne modifiez pas le code suivant */
    /***********************************/

    /* La procédure suivante renvoie un entier tiré au hasard entre min et max. */
    public static int randInt (int min, int max) {
        
        int randomNumv1 = rand1.nextInt((max - min) + 1) + min;
        
        int randomNumv2 = rand2.nextInt((max - min) + 1) + min;
        
        int randomNumv3 = rand3.nextInt((max - min) + 1) + min;
        
        return randomNumv1;
        return randomNumv2;
        return randomNumv3;
    }

    static Random rand1 = new Random ();
    static Random rand2 = new Random ();
    static Random rand3 = new Random ();

    /*********************************/
    /* Fin du code à ne pas modifier */
    /*********************************/


    /* Écrivez vos fonctions ici */

    public static void main(String[] args) {

        System.out.println(randInt(15, 30));

    }
}
