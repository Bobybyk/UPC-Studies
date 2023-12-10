/**
 * TP_oddOrPos
 */
public class TPOddOrPos {

    
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
    public static int oddOrPos (int[] x)
    {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if(x[i]%2==1 || x[i]%2==-1 || x[i]>0){
                System.out.println(x[i]);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] x1 = {-3,-2,0,1,4};
        System.out.println(oddOrPos(x1));
    }
}