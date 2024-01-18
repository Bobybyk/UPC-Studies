public class Stop extends Thread {
    private boolean finish;
    
    public Stop() { 
        finish = false; 
    }

    public void incr() {
        Main.check++;
        if (Main.check == 11) { 
            System.out.println("received 11 stop");
            this.finish=true;
        }
    }

    public void run() {
        super.run();
        while(!finish) {
            try { 
                sleep(1000);
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        incr();
        }
    }
}