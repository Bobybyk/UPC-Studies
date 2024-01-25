public class Exo {
    public static volatile boolean fait = false;
    public static int n;
    //1
    public static class Lecteur extends Thread {
    public void run() {
    while (!fait);
    System.out.println(n);
    }
    }
    public static void main(String[] args) throws InterruptedException {
    //2
    new Lecteur().start();
    Thread.sleep(100);
    n = 150;
    fait = true;
    System.out.println("fait");
    }
    }