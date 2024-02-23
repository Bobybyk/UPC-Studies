public class MyThread extends Thread {
    public SimpleSnap<Stamped<Integer>> partage;
    public int nb;
    
    public MyThread( SimpleSnap<Stamped<Integer>> partage, int nb){
        this.partage=partage;
        this.nb=nb;
    }

    public void run(){
        if (ThreadID.get()!=0) {
            Stamped<Integer> first = new Stamped<Integer>();
            first.reference = 1;
            first.stamp++;
            partage.update(first);

            Stamped<Integer> second = new Stamped<Integer>();
            second.reference = 2;
            second.stamp++;
            partage.update(second);

            Stamped<Integer> third = new Stamped<Integer>();
            third.reference = 3;
            third.stamp++;
            partage.update(third);
        } else {
            Object [] O=new Object[nb];
            O=partage.scan();
            System.out.print("scan de "+ThreadID.get() + ": ");
            for(int i=0;i<nb;i++){
                Stamped<Integer> stamp = (Stamped<Integer>)O[i];
                System.out.print(stamp.reference+" ");
            }
            System.out.println();
        }
    }
}