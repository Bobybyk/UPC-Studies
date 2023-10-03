package tp2.ex2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import tp2.ArrayBoundedQueue;

public class ArrayBoundedQueueTest {
    
    @Test
    public void testArrayBoundedQueueNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArrayBoundedQueue<>(-1);
        });
    }

    @Test
    public void testOccupancy() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(25);
        assertEquals(0, queue.occupancy(), "Initial queue is empty");
        queue.push(3);
        assertEquals(1, queue.occupancy(), "Added element did not grow size by 1"); 
        queue.push(2);
        queue.push(1);
        queue.pop();
        assertEquals(2, queue.occupancy(), "Pop and push are not changed"); 
    }

    @Test
    public void testFreeSlots() {
        fail("Not yet implemented");
    }

    @Test
    public void testClear() {
        fail("Not yet implemented");
    }

    @Test
    public void testPushFull() {
        
    }

    @Test
    public void testPopPush() {
        BoundedQueue<Integer> queue = new ArrayBoundedQueue<>(25);
        queue.push(3);
        queue.push(4);
        assertEquals(3, queue.pop(), "Pop did not retrieve first element");
        assertEquals(4, queue.pop(), "Pop did not retrieve first element");
    }

}
 