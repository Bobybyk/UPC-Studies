public class Main {
    public static void main(String[] args) {
        int nb=15;
        SimpleSnap<Stamped<Integer>> partage = new SimpleSnap<Stamped<Integer>>(nb, new Stamped<Integer>());
        MyThread R[]=new MyThread[nb];
        for (int i=0;i<nb;i++) {
            R[i]= new MyThread(partage,nb);
        }
        try {
            for (int i=0;i<nb;i++){
                R[i].start();
                if (i!=0) {
                    R[i].join();
                }
            }
        } catch(InterruptedException e){};
    }
}