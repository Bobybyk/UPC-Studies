package tp2.ex1;

public class FastFib {
    public static int fib(int n) {
        if (n < 0)
            throw new RuntimeException("Negative number");

        int f1 = 0, f2 = 1;
        for (; n > 0; n--) {
            int tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
        }
        return f2;
    }
}
