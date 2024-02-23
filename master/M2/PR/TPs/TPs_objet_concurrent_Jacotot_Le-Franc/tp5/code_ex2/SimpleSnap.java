public class SimpleSnap<T> implements Snapshot<T> {
    private T[] a_table;
    
    public SimpleSnap(int capacity, T init) {
        a_table= (T[]) new Object[capacity];
        for (int i=0;i<capacity;i++) {
            a_table[i]=init;
        }
    }

    public void update(T v) {
        int me=ThreadID.get();
        a_table[me]=v;
        
        //ne fait pas partie de l’implémentation
        try { 
            MyThread.sleep(1);
        } catch(InterruptedException e){};
        MyThread.yield();
    }

    private T[] collect() {
        T[] copy= (T[]) new Object[a_table.length];
        for(int j=0;j<a_table.length;j++) { 
            copy[j]=a_table[j];
            //ne fait pas partie de l’implémentation
            try { 
                MyThread.sleep(3);
            } catch(InterruptedException e){};
            MyThread.yield();
        }
        return copy;
    }

    public T[] scan() {
        T[] result1 = collect();
        T[] result2 = collect();
        for (int i = 0 ; i < result1.length ; i++) {
            Stamped<Integer> stamp1 = (Stamped<Integer>)result1[i];
            Stamped<Integer> stamp2 = (Stamped<Integer>)result2[i];
            if (stamp1.stamp != stamp2.stamp) {
                return scan();
            }
        }
        return result1;
    }
}