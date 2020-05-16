import java.util.*;

/* algorithme pour l'évaluation d'une forme postfixe:
 * on lit l'expression de gauche à droite
 * si un élément est un opérande, on l'empile
 * sinon (c'est un opérateur (binaire))
 *  * on dépile (deux) éléments
 *  * on exécute l'opération
 *  * on empile son résultat
 *
 * ci-dessous implémentation partielle (opérateur + uniquement)
 * à compléter...
 */

class Evaluation{
    public static void main(String[] args){
        System.out.println(evaluation(args));
    }
    static String evaluation(String[] e){
        Stack<Integer> p=new Stack<Integer>();//pile d'entier
        for(int i=0; i<e.length; i++){       //parcours de l'expression postfixe
            String s=e[i];
            if(s.equals("+")){
                if(p.empty()) return "postfixe mal formée";
                int e1 = p.pop();
                if(p.empty()) return "postfixe mal formée";
                int e2 = p.pop();
                p.push(e1+e2);
            }
            else p.push(Integer.valueOf(s));
        }
        if(p.empty()) return "postfixe mal formée";
        int e1 = p.pop();
        if(p.empty()) return "l'évaluation est " + e1;
        return "postfixe mal formée";
    }
}
