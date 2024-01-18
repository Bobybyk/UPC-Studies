public class Main {

    public static volatile int check=0;
    public static void main(String[] args) {
        MyObject object = new MyObject();
        Stop s = new Stop();
        s.start();
        object.start();
    }
}