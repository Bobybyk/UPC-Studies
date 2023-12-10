import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TPLastZero {

        /*
                Tester la fonction suivante. Répondre aux questions :
                1) Expliquez ce qui ne va pas avec le code donné. Décrivez le défaut
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
        public static int lastZero(int [] x) {
                for (int i = 0; i < x.length; i++) {
                        if (x[i] == 0) return i;
                }
                return -1;
        }
        /*
                MAIN
        */
        public static void main(String[] args) {
                int[] x1 = {0, 1, 0};
                System.out.println(lastZero(x1));
        }


        /*
        Question 1)
        -> Le défaut est dans la méthode lastZero qui commence à chercher des
        zéros à l’index 0 au lieu de l’index de fin du tableau x, comme cela est nécessaire pour
        les tableaux en Java. Si l'on veut la dernière occurence.
        Par exemple, lastZero ({1, 7, 0}) est correctement évalué à la postion 2, tandis
        que numZero ({0, 7, 0}) est évalué incorrectement à la position 0.
        Voici une correction possible:
        */        
        public static int lastZero_correction(int [] x) {
                for (int i = x.length-1; i >= 0; i--) {
                        if (x[i] == 0) return i;
                }
                return -1;
        }

        /*
        Question 2)
        -> x = {}
        On ne rentre jamais dans le for dans ce cas là et donc le défaut n'est jamais executé
        */
        @Test
        public void testQuestion2() {
                int[] x1 = {};
                assertEquals(-1, lastZero(x1));
        }

        /*
        Question 3)
        Il n'existe pas de cas test passant par le défaut et n'executant pas l'erreur.
        Cet état est erroné précisément parce que la valeur de i devrait être la longueur du tableau à la première itération,
        et on devrait aller de la fin vers le début si on veut retourner i dès qu'on trouve zéro.
        */

        /*
        Question 4)
        -> x = {...,0,..} un tableau avec un seul 0 executera le défaut et donc l'erreur, mais en regardant la
        valeur retournée on ne le remarque pas puisqu'il retournera bien l'indice de la dernière occurence de zéro et on
        ne voit donc pas la défaillance.
        */
        @Test
        public void testQuestion4() {
                int[] x1 = {1, 1, 0};
                assertEquals(2, lastZero(x1));
        }

        /*
        5) Pour le scénario de test donné, décrivez le premier état d’erreur.
           Assurez-vous de décrire l’état complet (avec le program counter).
        -> x = {0, 1, 0} (scénario pour décrire le premier état d’erreur)
        Dans ce scénario, le tableau contient un zéro à l'indice 0, et le défaut (qui commence à chercher des zéros à l'indice 0 au lieu de l'indice de fin du tableau) sera exécuté. 
        La fonction lastZero va renvoyer 0 au lieu de 2. Le premier état d'erreur se produit lors de la première itération de la boucle, où l'état complet est :
        - x = {0, 1, 0}
        - i = 0
        - PC = "i < x.length"
        Cet état est erroné car la fonction cherche des zéros à l'indice 0, alors que pour trouver le dernier zéro, elle devrait commencer à l'indice de fin du tableau.
        */
}