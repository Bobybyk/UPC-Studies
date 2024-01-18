public class TPC2Ex2 {
    public static void main(String[] args) {
        MonObjet o= new MonObjet(0);
        MyThread2Ex2 W;
        MyThread2Ex2 R;
        W= new MyThread2Ex2(o,1000);
        R= new MyThread2Ex2(o,5000);
        W.start();
        R.start();
        try{
            R.join();
            W.join();
        } catch(InterruptedException e){};
        synchronized (o) {
            System.out.println("value : " + o.value + ", valuebis : " + o.valuebis + " et last : " + o.last.get());
        }       
    }
}