package tp2.ex1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestFib {
    
   @Test
    public void testFib() {
        assertEquals(610, Fib.fib(15));   
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 12, 15, 20, 30})
    public void testFib(int n) {
        assertEquals(Fib.fib(n), Fib.fib(n));
    }

}

