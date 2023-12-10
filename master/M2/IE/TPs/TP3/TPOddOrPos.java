import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
    
    /*
     * 1 Expliquez ce qui ne va pas avec le code donné. Décrivez le défaut
     * précisément en proposant une modification du code.
     *  -> Lors du if on ne prend pas en compte les nombres impairs négatif qui seront égaux à
     *     -1 quand ils sont mit à modulo 2 et non pas 1. Il faut donc ajouter un || x[i]%2==-1
     *     dans la cond
     * 
     * 3 Si possible, donnez un scénario de test qui exécute le défaut, sans
     * l’apparition d’une erreur. Sinon, expliquez brièvement pourquoi.
     *  -> Un talbeau qui n'a pas aucun nombres impairs négatif n'executerais pas le défaut
     * 
     * 4 Si possible, donnez un scénario de test qui entraîne une erreur, mais
     * sans une défaillance visible. Sinon, expliquez brièvement pourquoi.
     *  -> 
     * 
     * 5 Pour le scénario de test donné, décrivez le premier état d’erreur.
     * Assurez-vous de décrire l’état complet (avec le program counter).
     *  ->
     * 
     */

public class TPOddOrPos {

        /*
                1)Expliquez ce qui ne va pas avec le code donné. Décrivez le défaut
                précisément en proposant une modification du code.
                2) Si possible, donnez un scénario de test qui n’exécute pas le défaut.
                Sinon, expliquez brièvement pourquoi.
                3) Si possible, donnez un scénario de test qui exécute le défaut, sans
                l’apparition d’une erreur. Sinon, expliquez brièvement pourquoi.
                4) Si possible, donnez un scénario de test qui entraîne une erreur, mais
                sans une défaillance visible. Sinon, expliquez brièvement pourquoi.
                5) Pour le scénario de test donné, décrivez le premier état d’erreur.
                Assurez-vous de décrire l’état complet (avec le program counter).

                ENONCE
        */
    public static int oddOrPos (int[] x)
    {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if(x[i]%2==1 || x[i]>0){
                System.out.println(x[i]);
                count++;
            }
        }
        return count;
    }
        /*
                MAIN
        */
        
    public static void main(String[] args) {
        int[] x1 = {-3,-2,0,1,4};
        System.out.println(oddOrPos(x1));
    }

        
        /*
        Question 1)
        -> La condition actuelle ne tient pas compte des nombres négatifs impairs.
        Par exemple, lastZero ({-2,0,1,4}) est correctement évalué à 2, tandis
        que numZero ({-3,-2,0,1,4}) est évalué incorrectement à 3 au lieu de 4.
        */        
    public static int oddOrPos_correction(int[] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] % 2 == 1|| x[i]%2==-1 || x[i] > 0) {
                System.out.println(x[i]);
                count++;
            }
        }
        return count;
    }

        /*
        Question 2)
        -> x1 = {-2, -4, 0} dans ce scénario, le tableau ne contient que des nombres négatifs et zéro,
        donc la condition actuelle ne sera jamais vraie, et le défaut ne sera pas exécuté.
        */
        @Test
        public void testQuestion2() {
                int[] x1 = {-2, -4, 0};
                assertEquals(0, oddOrPos(x1));
        }

        /*
        Question 3)
        Il n'existe pas de cas test passant par le défaut et n'executant pas l'erreur.
        Cet état est erroné précisément parce que la condition de la boucle est fausse donc is on rentre le if le défaut et
        l'erreur sont executés.
        */

        /*
        Question 4)
        -> x = {1,2,3} un tableau avec des termes positif, et pas de termes négatifs impairs executera le défaut et donc l'erreur,
        mais en regardant la valeur retournée on ne le remarque pas puisqu'il retournera bien le nombre de valeur positf ou impairs et on
        ne voit donc pas la défaillance.
        */
        @Test
        public void testQuestion4() {
                int[] x1 = {1, 2, 3};
                assertEquals(3, oddOrPos(x1));
        }

        /*
        5) Pour le scénario de test donné, décrivez le premier état d’erreur.
           Assurez-vous de décrire l’état complet (avec le program counter).
        Le défaut sera exécuté dès la première itération de la boucle.
        Premier état d'erreur :
        - x = {-3, -2, 0, 1, 4}
        - i = 0
        - PC = "x[i] % 2 == 1 || x[i] > 0"
        Cet état est erroné car la condition actuelle ne tient pas compte des nombres négatifs impairs.
        */
}