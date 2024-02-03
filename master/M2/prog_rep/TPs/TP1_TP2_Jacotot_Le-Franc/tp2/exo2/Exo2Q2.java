package exo2;
import java.util.concurrent.locks.*;

public class Exo2Q2 {
    public static class Critique implements Runnable {
        private Lock lock;
        private int nb;
        private int id;

        public Critique(Lock lock, int id) {
            this.lock = lock;
            this.nb = 0;
            this.id = id;
        }

        public void run() {
            while (nb < 20) {
                
                lock.lock();

                // Section critique
                try {
                    System.out.println("la thread " + id + " entre en section critique pour la " + nb + " fois");
                    nb++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            // Simulation en dehors de la section critique
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Critique(lock, i)).start();
        }
    }
}