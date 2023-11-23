/**
 * Implémentation de Gabriel
 */

import java.time.Duration;

public class FeuTriColor {

    public int  feu_actuel = 0;

    public void feuTriColor_start(){
        while (true) {
            switch (feu_actuel) {
                case 0:
                    feu_actuel++;
                    System.out.println("\u001B[31m O");
                    System.out.println("\u001B[30m O");
                    System.out.println("\u001B[30m O");
                    System.out.println("-----");
                    try {
                        Thread.sleep(Duration.ofSeconds(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }                   
                    break;
            
                case 1:
                    feu_actuel++;
                    System.out.println("\u001B[30m O");
                    System.out.println("\u001B[33m O");
                    System.out.println("\u001B[30m O");
                    System.out.println("-----");
                    try {
                        Thread.sleep(Duration.ofSeconds(2));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }  
                    break;
                case 2:
                    feu_actuel=0;
                    System.out.println("\u001B[30m O");
                    System.out.println("\u001B[30m O");
                    System.out.println("\u001B[32m O");
                    System.out.println("------");
                    try {
                        Thread.sleep(Duration.ofSeconds(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                    break;
                default:
                    System.err.println("BAD CODE");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        FeuTriColor s = new FeuTriColor();
        s.feuTriColor_start();
    }
}