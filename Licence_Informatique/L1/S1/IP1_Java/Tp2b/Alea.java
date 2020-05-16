/* Pour pouvoir générer des nombres aléatoires */
import java.util.Random;

public class Alea {

    /***********************************/
    /* Ne modifiez pas le code suivant */
    /***********************************/

    /* La procédure suivante renvoie un entier tiré au hasard entre min et max. */
    public static int randInt (int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static int de() {

	return randInt(1,6);

    }

    public static void yams() {

	int x1 = de();
	int x2 = de();
	int x3 = de();

	if (1 < 2 && 1 < 3) {
	   System.out.println(1);
	   System.out.println(2); 
	   System.out.println(3);
 	}
	else if (1 < 2 && 3 < 2) {
	   System.out.println(1);
           System.out.println(2);
	   System.out.println(3);
        }
	else if (2 < 1 && 1 < 3) {
   	   System.out.println(1);
	   System.out.println(2);
	   System.out.println(3);
	}
	else if (2 < 1 && 3 < 1) {
	   System.out.println(1);
	   System.out.println(2);
	   System.out.println(3);
	}
	 

    }

    static Random rand = new Random ();

    /*********************************/
    /* Fin du code à ne pas modifier */
    /*********************************/


    /* Écrivez vos fonctions ici */

    public static void main(String[] args) {

        /* Écrivez vos tests */
	

    }
}
