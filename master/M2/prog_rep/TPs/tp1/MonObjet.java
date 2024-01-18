public class MonObjet {
    ThreadLocal<Integer> last;//nb ecriture de chaque thread
    double value;//valeur commune
    double valuebis;//valeur commune
    
    public MonObjet(int init){
        value=init;
        last=new ThreadLocal<Integer>(){
            protected Integer initialValue() {
                return 0;
            }
        };
    }

    public double read() { 
        return value;
    }

    public void add(){
        last.set(new Integer(last.get()+1));
        value = value + 1;
        valuebis = valuebis + 1;
    }
}
