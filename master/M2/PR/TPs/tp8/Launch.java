public class Launch {

    public static void main(String[] args) {
        AtomicList set = new AtomicList();
        ThreadSet threadSet_1 = new ThreadSet(set);
        ThreadSet threadSet_2 = new ThreadSet(set);
        ThreadSet threadSet_3 = new ThreadSet(set);

        threadSet_1.start();
        threadSet_2.start();
        threadSet_3.start();

        try {
            threadSet_1.join();
            threadSet_2.join();
            threadSet_3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
