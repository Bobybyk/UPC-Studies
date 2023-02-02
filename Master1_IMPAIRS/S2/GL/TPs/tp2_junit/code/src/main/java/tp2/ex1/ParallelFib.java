package tp2.ex1;

import java.util.concurrent.RecursiveTask;

public class ParallelFib extends RecursiveTask<Integer> {
    int n;

    public ParallelFib(int n) {
        this.n = n;
    }

    protected Integer compute() {
        if (n < 0) {
            throw new RuntimeException("Negative number");
        } else if (n <= 1) {
            return n;
        }
        ParallelFib f1 = new ParallelFib(n - 1);
        f1.fork();
        ParallelFib f2 = new ParallelFib(n - 2);
        return f2.compute() + f1.join();
    }

    public static int fib(int n) {
        ParallelFib f = new ParallelFib(n);
        return f.compute();
    }
}
