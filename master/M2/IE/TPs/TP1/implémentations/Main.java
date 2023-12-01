/**
 * Implémentation de Hugo
 */

public class Main {

    //0 = green
    //1 = orange
    //2 = red
    private static int color = 1;

    public static void main(String[] args) {

        while(true) {
            try {

                printColor();
                int time = 0;
                switch(color) {
                    case 0:
                        time = 5;
                        break;
                    case 1:
                        time = 1;
                        break;
                    case 2:
                        time = 5;
                        break;
                }

                Thread.sleep(time * 1000);

                color = (color + 1) % 3;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void printColor() {

        System.out.println("---");
        switch(color) {
            case 0:
                System.out.println(" \u001B[0m⬤ ");
                System.out.println(" \u001B[0m⬤ ");
                System.out.println(" \u001B[32m⬤ ");
                break;
            case 1:
                System.out.println(" \u001B[0m⬤ ");
                System.out.println(" \u001B[33m⬤ ");
                System.out.println(" \u001B[0m⬤ ");
                break;
            case 2:
                System.out.println(" \u001B[31m⬤ ");
                System.out.println(" \u001B[0m⬤ ");
                System.out.println(" \u001B[0m⬤ ");
                break;
        }
        System.out.println("---");
    }
}