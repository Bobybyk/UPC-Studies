import java.util.Iterator;

public class TabSet<E> implements Iterable<E>{
    private E[] tableau;

    @SuppressWarnings("unchecked")
    public TabSet(int n) {
        for (int i = 0 ; i <= n ; i++) {
            this.tableau = (E[]) new Object[n];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public boolean contains (Object o) {
        Iterator<E> ite = iterator();
        if (tableau[0] != null && tableau[0].equals(o)) {
            return true;
        }
        while (ite.hasNext()) {
            if (ite.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        Iterator<E> ite = iterator();
        int tmp = 0;
        if (tableau[0] != null) {
            tmp++;
        }
        while(ite.hasNext()) {
            if (ite.next().equals(null)) {
                tmp++;
            } 
        }
        return tmp;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } return false;
    }

    public boolean add(E e) throws IllegalStateException {
        if (!contains(e)) {
            if (e != null) {
                Iterator<E> ite = iterator();
                ((TabIter) ite).add(e);
                return true;
            }
        } return false;
    }

    public class TabIter implements Iterator<E> {
        private int index;
        //private E[] tableau;

        public TabIter(E[] tab) {
            tableau = tab;
        }

        public void add(E e) throws IllegalStateException{
            while (hasNext()) {
                if (next() == null) {
                    tableau[index] = e;

                }
            }
            throw new IllegalStateException();
        }

        public boolean remove(Object o) {
            if (contains(o)) {
                Iterator<E> ite = iterator();
                ((TabIter) ite).remove();
                return true;
            } return false;
        }

        @Override
        public boolean hasNext() {
            return index+1 < tableau.length;
        }

        @Override
        public E next() {
            return tableau[++index];
        }

    }
}
