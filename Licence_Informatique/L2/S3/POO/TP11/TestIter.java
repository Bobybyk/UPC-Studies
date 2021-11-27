import java.util.Iterator;

public class TestIter<E> implements Iterator<E> {
    private E[] tableau;
    private int index;

    public TestIter(E[] tab) {
        this.tableau = tab;
    }

    @Override
    public boolean hasNext() {
        return this.index+1 < tableau.length && tableau[this.index+1] != null;
    }

    @Override
    public E next() {
        return this.tableau[++this.index];
    }

}