import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Exo2Q6 implements Lock {
    // Flag de chaque thread
    private boolean[] flag;
    // Label de chaque thread
    private int[] label;
    // Nombre de threads
    private int n;

    public Exo2Q6(int n) {
        this.n = n;
        flag = new boolean[n];
        label = new int[n];
    }

    public void lock() {
        int i = (int) Thread.currentThread().getId();
        System.out.println("Thread " + i + ", n = " + n);
        flag[i] = true;
        label[i] = max(label) + 1;
        for (int j = 0 ; j < n ; j++) {
            while (j != i && flag[j] && (label[j] < label[i] || (label[j] == label[i] && j < i)));
        }
    }

    public void unlock() {
        flag[(int) Thread.currentThread().getId()] = false;
    }

    private int max(int[] tab) {
        int max = tab[0];
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        return max;
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Exo2Q6(26);

        // Thread 0
        new Thread(() -> {
            System.out.println("Thread 0 essaie d'entrer en SC");
            lock.lock();
            System.out.println("Thread 0 est en SC");
            // Simuler un travail en section critique
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("Thread 0 sort de SC");
        }).start();

        // Attendre un peu avant de lancer d'autres threads
        Thread.sleep(500);

        // Thread 1
        new Thread(() -> {
            System.out.println("Thread 1 essaie d'entrer en SC");
            lock.lock();
            System.out.println("Thread 1 est en SC");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("Thread 1 sort de SC");
        }).start();

        // Thread 2
        new Thread(() -> {
            System.out.println("Thread 2 essaie d'entrer en SC");
            lock.lock();
            System.out.println("Thread 2 est en SC");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("Thread 2 sort de SC");
        }).start();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lockInterruptibly'");
    }

    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tryLock'");
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tryLock'");
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newCondition'");
    }
}