import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.Random;
import java.util.Random;

public class G2048 {

    // ---------------------------------------------------------------------- //
    // Fonctions utilitaires que vous pourrez utiliser pour implémenter les
    // questions des exercices

    static Random rand = new Random ();

    /* La fonction suivante renvoie un entier tiré au hasard entre min et max. */
    public static int randInt (int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /* Procédure affichant le contenu d'un tableau d'entiers. */
    public static void printIntArray(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /* Procédure inversant les éléments d'un tableau

       Exemple : entrée : {1,2,3,4,5}, sortie : {5,4,3,2,1}
     */
    public static void reverse(int[] t) {
        for(int i = 0; i < t.length / 2; i++) {
            int tmp = t[i];
            t[i] = t[t.length - i - 1];
            t[t.length - i - 1] = tmp;
        }
    }

    /* Procédure inversant les lignes et colonnes d'un tableau de tableaux carré
       (les lignes deviennent les colonnes, et les colonnes deviennent les
       lignes).

       Exemple :
       entrée :
         { {0, 1, 2},
           {3, 4, 5},
           {6, 7, 8} }

       sortie :
         { {0, 3, 6},
           {1, 4, 7},
           {2, 5, 8} }
    */
    public static void transpose(int[][] t) {
        for(int i = 0; i < t.length; i++) {
            for(int j = i+1; j < t.length; j++) {
                int tmp = t[i][j];
                t[i][j] = t[j][i];
                t[j][i] = tmp;
            }
        }
    }

    // ---------------------------------------------------------------------- //

    // La grille
    public static int [][]board = new int [4][4];
    //public static int[][] board;

    // Taille de la grille
    public static int boardSize = 4;

    // Coups :
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int UP = 2;
    public static int DOWN = 3;

    public static void initBoard () {

        board[0][1] = 2;
        board[2][2] = 2;

        System.out.println((java.util.Arrays.deepToString(board)));        

    }


    public static boolean isBoardWinning () {

        for (int i = 0 ; i<3 ; i++) {
            for (int j = 0 ; j<3 ; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;

    }


    public static int newSquareValue () {

        Random rand = new Random(); 
        
        int r = rand.nextInt(100) + 0;

        if (r <89) {
            return 2;
        }

        return 4;
    }


    public static int[] newEmptyRow () {

        int []tabEmpty = new int[boardSize];

        return tabEmpty;

    }


    public static int[] slideLeftAndMerge (int []tabLeft) {

        int p = 0;

        for (int i = 0 ; i<3 ; i++) {
            if (tabLeft[0] == tabLeft[1]) {
                tabLeft[0] = tabLeft[0] + tabLeft[1];
                tabLeft[1] = tabLeft[2];
                tabLeft[2] = tabLeft[3];
                tabLeft[3] = 0;
            } 
            if (tabLeft[0] == 0) {
                tabLeft[0] = tabLeft[1];
                tabLeft[1] = tabLeft[2];
                tabLeft[2] = tabLeft[3];
                tabLeft[3] = 0;
            } 
            if (tabLeft[1] == tabLeft[2]) {
                tabLeft[1] = tabLeft[1] + tabLeft[2];
                tabLeft[2] = tabLeft[3];
                tabLeft[3] = 0;
            } 
            if (tabLeft[1] == 0) {
                tabLeft[1] = tabLeft[2];
                tabLeft[2] = tabLeft[3];
                tabLeft[3] = 0;
            }  
            if (tabLeft[2] == tabLeft[3]) {
                tabLeft[2] = tabLeft[2] + tabLeft[3];
                tabLeft[3] = 0;
            }   
            if (tabLeft[2] == 0) {
                tabLeft[2] = tabLeft[3];
                tabLeft[3] = 0;
            } 
        }

        if (tabLeft[0] < tabLeft[1]) {
            p = tabLeft[0];
            tabLeft[0] = tabLeft[1];
            tabLeft[1] = p;
        }
        return tabLeft;

    }


    public static void slideBoard(int direction) {
        // todo
        return;
    }

    // Exercice 4 :
    // Complétez addSquare

    public static void addSquare(int value) {
        // todo
        return;
    }

    // Exercice 5 :
    // Implémentez ce qui est demandé ici.

    public static boolean isValidMove(int direction) {
        // todo
        return true;
    }

    public static void main(String[] args) {
        
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.print("initBoard = ");
        initBoard();
        System.out.print("newSquareValue = ");
        System.out.println(newSquareValue());
        System.out.print("isBoardWinning = ");
        System.out.println(isBoardWinning());
        System.out.print("newEmptyRow = ");
        System.out.println(java.util.Arrays.toString(newEmptyRow()));
        int[] row = {0,2,2,2};
        System.out.print("slideLeftAndMerge = ");
        printIntArray(slideLeftAndMerge(row));
        System.out.print("runGame = ");
        runGame();
        
    }

    // ---------------------------------------------------------------------- //
    // Ci-dessous, fonctions qu'il n'est pas forcément nécessaire de comprendre.
    //

    // move exécute un tour de jeu : si le coup est valide, alors on décale les
    // cases de la grille, et on en ajoute une nouvelle
    public static void move(int direction) {
        if (isValidMove(direction)) {
            slideBoard(direction);
            addSquare(newSquareValue());
        }
    }

    // dessine la grille courante
    public static void drawBoard() {
        StdDraw.clear();
        for(int i = 0; i <= boardSize; i++) {
            StdDraw.line(0, 0.25 * i, 1, 0.25 * i);
            StdDraw.line(0.25 * i, 0, 0.25 * i, 1);
        }

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    StdDraw.text(j * 0.25 + 0.125,
                                 1 - i * 0.25 - 0.125,
                                 Integer.toString(board[i][j]));
                }
            }
        }
        StdDraw.show();
    }

    // récupère la direction indiquée au clavier
    public static int getDirection() {
        int direction = -1;
        while(direction == -1) {
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                direction = LEFT;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                direction = RIGHT;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                direction = DOWN;
            } else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                direction = UP;
            }
            StdDraw.pause(16);
        }
        StdDraw.clearKeyPressed();
        return direction;
    }

    // boucle principale du jeu : fait jouer des coups jusqu'à ce que la partie
    // soit gagnée
    public static void runGame() {
        Font font = new Font("Sans Serif", Font.PLAIN, 40);
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(font);
        StdDraw.enableDoubleBuffering();

        initBoard();
        drawBoard();

        while(!isBoardWinning()) {
            int direction = getDirection();
            move(direction);
            drawBoard();
        }
    }
}
