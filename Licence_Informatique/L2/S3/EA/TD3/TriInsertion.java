public class TriInsertion {
 
    public static int[] triCroissantInsertion(int[] t) {
        for (int i = 0 ; i < t.length ; i++) {
            int tmp = t[i];
            int j = i;
            while(j > 0 && t[j-1] > tmp) {
                t[j] = t[j-1];
                j--;
            }
            t[j] = tmp;
        }
        return t;
    }

    public static void affiche(int[] t) {
        for (int i = 0 ; i < t.length ; i++) {
            System.out.print(t[i] + ", ");      
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] t = {3, 3, 4, 1, 2, 10, 9, 8};
        affiche(t);
        affiche(triCroissantInsertion(t));
    }

}
