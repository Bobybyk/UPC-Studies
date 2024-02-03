package exo2;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Exo2Q1 implements Lock, Runnable {
    private boolean[] flag;
    private int nb;
    volatile int victim;

    public Exo2Q1() {
        this.nb = 0;
        this.flag = new boolean[2];
    }

    public void run() {
        while (nb < 20) {
            lock();

            // Section critique
            try {
                System.out.println("la thread entre en section critique pour la " + nb + " fois");
                nb++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }

        // Simulation en dehors de la section critique
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        int i = ThreadId.get();
        int j = 1 - i;
        flag[i] = true;
        victim = i ;
        while (flag[j] && victim == i) {}; // wait
    }

    @Override
    public void unlock() {
        int i = ThreadId.get();
        flag[i]=false; // I’m not interested
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {}

    @Override
    public boolean tryLock() {return false;}

    public static void main(String[] args) {
        Exo2Q1 lock = new Exo2Q1();
        for (int i = 0; i < 10; i++) {
            new Thread(lock).start();
        }
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