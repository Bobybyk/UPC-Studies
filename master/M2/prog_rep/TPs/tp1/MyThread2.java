public class MyThread2 extends Thread{
    public int nbwrite; int x;
    public ThreadID tID;
    public int myid;
    
    public MyThread2(String name) {
        this.nbwrite=nbwrite;
        tID=new ThreadID();
        myid=tID.get();
    }

    public void run(){
        // tID=new ThreadID();
        // myid=tID.get();
        System.out.println("la thread "+tID.get() + " "+myid);
        try{
            this.sleep(10);
        } catch(Exception e){
            e.printStackTrace(); 
        }
        System.out.println("la thread " +tID.get() + "apres le sommeil ");
    }
}