public class MyObject extends Thread {
    public void work() { 
        int cur;
        for(cur=Main.check;cur!=10;) {
            if (Main.check>cur) { 
                System.out.println("check = "+Main.check+ " cur = "+cur);
                cur=Main.check;
            }
        }
    }

    public void run() {
        super.run(); work(); System.out.println("coucou");
    }
}