import java.util.ArrayList;

public class Launch {



    public static void main(String[] args) {

        System.out.println("-----EXERCICE 1.3-------\n");

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

        System.out.println("\n-----EXERCICE 2 : TEST AND COMPARE-------\n");

        long timeStart = System.currentTimeMillis();
        set = new AtomicList();

        ArrayList<ThreadSet> list = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            list.add(new ThreadSet(set));

        for (ThreadSet thread: list)
            thread.start();

        try {
            for (ThreadSet thread: list)
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long timeEnd = System.currentTimeMillis() - timeStart;
        System.out.println(timeEnd + " ms");

    }
}
