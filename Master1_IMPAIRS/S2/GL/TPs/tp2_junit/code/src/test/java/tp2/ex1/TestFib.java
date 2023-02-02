import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tp2.ex1.Fib;

public class TestFib {

    @tp2.ex1.Test
    public void testFib() {
        assertEquals(377, Fib.fib(15));
    }

}
