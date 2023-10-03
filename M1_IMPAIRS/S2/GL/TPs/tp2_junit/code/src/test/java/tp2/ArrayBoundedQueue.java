package tp2;

import tp2.ex2.BoundedQueue;
import tp2.ex2.EmptyQueueException;
import tp2.ex2.FullQueueException;

public class ArrayBoundedQueue<T> implements BoundedQueue<T> {

    private T[] tab;
    private int counterElements;
    private int counterFreeSpace;

    public ArrayBoundedQueue(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.tab = (T[]) new Object[size];
        this.counterElements = 0;
        this.counterFreeSpace = size;

    }

    @Override
    public int occupancy() {
        return counterElements;
    }

    @Override
    public int freeSlots() {
        return counterFreeSpace;
    }

    @Override
    public void clear() {
        counterFreeSpace = counterElements+counterFreeSpace;
        counterElements = 0;
    }

    @Override
    public void push(T elem) throws FullQueueException {
        if (counterFreeSpace == 0) {
            throw new FullQueueException();
        }
        tab[counterElements] = elem;
        counterElements++;
        counterFreeSpace--;
    }

    @Override
    public T pop() throws EmptyQueueException {
        if (counterElements == 0) {
            throw new EmptyQueueException();
        }
        T elem = tab[0];
        for (int i = 1; i < counterElements; i++) {
            tab[i-1] = tab[i];
        }
        counterElements--;
        counterFreeSpace++;
        return elem;
    }
    
}
