public class ThreadSet extends Thread{

    private AtomicList set;

    ThreadSet(AtomicList set){
        this.set = set;
    }

    public AtomicList getSet() {
        return set;
    }


    @Override
    public void run() {

        boolean b;

        b = set.add(ThreadID.get());
        System.out.println("résultat add" + b + " id:" + ThreadID.get() + " " + set);

        b = set.add(ThreadID.get() * 2);
        System.out.println("résultat add" + b + " id:" + ThreadID.get() + " " + set);

        b = set.contains(ThreadID.get() + 5);
        System.out.println("résultat contains" + b + " id:" + ThreadID.get() + " " + set);

        b = set.remove(ThreadID.get() * 2);
        System.out.println("résultat remove" + b + " id:" + ThreadID.get() + " " + set);
        
    }
}
