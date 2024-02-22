import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.UnsupportedOperationException;

public class RegInt {

    private AtomicBoolean tab[];

    public RegInt(int max) {
        tab = new AtomicBoolean[max + 1];
    }

    public int read() {

        int i = 0;
        while (tab[i].get() == false && i < tab.length) {
            i++;
        }

        if (i == tab.length) {
            throw new UnsupportedOperationException();
        }

        return i + 1;
    }

    public void update(int value) {

        int i = 0;
        while (tab[i].get() == false && i < tab.length) {
            i++;
        }

        if (i == tab.length) {
            throw new UnsupportedOperationException();
        }

        if (i + 1 > value) {
            tab[value + 1].set(true);
            tab[i + 1].set(false);
        } else {
            tab[i + 1].set(false);
            tab[value + 1].set(true);
        }
    }

}