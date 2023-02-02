package tp2.ex1;

public class Fib {
    public static int fib(int n) {
        if (n < 0) {
            throw new RuntimeException("Negative number");
        } else if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
