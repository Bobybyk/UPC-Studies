import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TabSet<E> implements Iterable<E>, Set<E>{
    private E[] tableau;

    @SuppressWarnings("unchecked")
    public TabSet(int n) {
        this.tableau = (E[]) new Object[n];
    }

    @Override
    public Iterator<E> iterator() {
        return new TabIter(tableau);
    }

    public boolean contains (Object o) {
        Iterator<E> ite = iterator();
        if (tableau[0] != null && tableau[0].equals(o)) {
            return true;
        }

        while (ite.hasNext()) {
            if (ite.next() == o) {
                return true;
            }
        }
        return false;
    }

    public void afficher() {
        for (int i = 0 ; i<tableau.length ; i++) {
            System.out.println(tableau[i]);
        }
    }

    public int size() {
        Iterator<E> ite = iterator();
        int tmp = 0;
        if (tableau[0] != null) {
            tmp++;
        }
        while(ite.hasNext()) {
            if (ite.next() != null) {
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

    public boolean add(E e) {
        if (!contains(e)) {
            if (e != null) {
                Iterator<E> ite = iterator();
                ((TabIter) ite).add(e);
                return true;
            }
        } return false;
    }

    public boolean remove(Object o) {
        Iterator<E> ite = iterator();
        if (tableau[0] == o) {
            ite.remove();
        }
        
        if (contains(o)) {
            while(ite.hasNext()) {
                if (ite.next() == o) {
                    ((TabIter) ite).remove();
                    return true;
                }
            }
        } return false;
    }

    public void clear() {
        Iterator<E> ite = iterator();
        ite.remove();
        while(ite.hasNext()) {
            ite.next();
            ite.remove();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] t = new Object[size()];
        int tmp = 0;
        for (Object o : t) {
            if (o != null) {
                t[tmp] = o;
                tmp++;
            }
        } 
        return t;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        for (Object o : a) {
            if (o != null) {
                //this.tableau[index] = a[o];
            }
            
        }
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element: c) {
            add(element);
        } return containsAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object element: c) {
            if (!contains(element)) {
                remove(element);
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element: c) {
            remove(element);
        }
        return !containsAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    public class TabIter implements Iterator<E> {
        private int index;
        //private E[] tableau;

        public TabIter(E[] tab) {
            tableau = tab;
        }

        public void add(E e) throws IllegalStateException{
            if (tableau[0] == null) {
                tableau[0] = e;
            }
            else {    
                while (hasNext()) {
                    if (next() == null) {
                        tableau[index] = e;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public void remove() {
            tableau[index] = null;
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
