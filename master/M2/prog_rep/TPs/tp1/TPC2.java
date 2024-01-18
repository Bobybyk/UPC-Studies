public class TPC2 {
    
    public static void main(String[] args) {

        MyThread2 TH[]= new MyThread2[10];
        
        for (int i = 0 ; i < 10 ; i++) {
            TH[i]=new MyThread2("nom"+i);
        }
        try {
            for(int i=0;i<10; i++) TH[i].start();
            for(int i=0;i<10; i++) TH[i].join();
        } catch(InterruptedException e){};
    }
}