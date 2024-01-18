public class MyThread2Ex2 extends Thread{
    public MonObjet o;
    public int nbwrite;

    public MyThread2Ex2(MonObjet o,int nbwrite){
        this.o=o;
        this.nbwrite=nbwrite;
    }

    public void run(){
        for(int i=0;i<nbwrite;i++) {
            o.add();
            this.yield();
        }
        System.out.println("la thread "+ThreadID.get()+" a pour last "+o.last.get());
    }
}