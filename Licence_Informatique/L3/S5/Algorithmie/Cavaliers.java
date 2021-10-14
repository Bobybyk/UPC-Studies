public class Cavaliers {
    private String[][] grille;
    private int[][] sauts = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};

    private Cavaliers(int x, int y) {
        grille = new String[x][y];
    }
    public static void main(String[] args) {
        Cavaliers jeu = new Cavaliers(4, 4);
        jeu.remplis(0, 0, "A");
        jeu.remplis(1, 0, ".");
        jeu.remplis(1, 1, ".");
        jeu.remplis(1, 2, ".");
        jeu.remplis(1, 3, "C");
        jeu.remplis(2, 0, "D");
        jeu.remplis(2, 1, "B");
        jeu.remplis(2, 2, ".");
        jeu.remplis(3, 0, ".");
        jeu.remplis(3, 1, ".");
        jeu.affiche();
    }

    private void echange(int xd, int yd, int xa, int ya) {
        if (grille != null) {
            if (grille[xd][yd] != null && grille[xa][ya] != null) {
                if (!Character.isLetter(grille[xa][ya].charAt(0))) {

                }
            }
        }
    }

    private void remplis(int x, int y, String c) {
        if (grille != null) {
            grille[x][y] = c;
        } else {
            System.out.println("Grille non définie");
        }
    }

    private void affiche() {
        if (grille != null) {
            for (int i = 0 ; i<grille.length ; i++) {
                for (int j = 0 ; j<grille[i].length ; j++) {
                    System.out.print(grille[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Grille non définie");
        }
    }
}