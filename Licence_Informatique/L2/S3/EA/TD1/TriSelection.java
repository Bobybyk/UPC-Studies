public class TriSelection {
    
    public static int[] triCroissantSelection(int[] t) {
        for (int i = 0 ; i<t.length-1 ; i++) {
            int min = i;
            for (int j = i+1 ; j<t.length ; j++) {
                if (t[j] < t[min]) {
                    min = j;
                }
            }
            int tmp = t[i];
            t[i] = t[min];
            t[min] = tmp;
        }
        return t;
    }

    public static void affiche(int[] t) {
        for (int i = 0 ; i<t.length ; i++) {
            System.out.print(t[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] t = {10, 1, 8, 4, 2, 9};
        affiche(t);
        affiche(triCroissantSelection(t));
    }
}
