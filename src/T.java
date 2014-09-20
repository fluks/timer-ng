
import java.util.Random;


public class T extends Thread {
    private int i;

    public T(int i) {
        this.i = i;
    }

    public void play() {
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void run() {
        int n = 0;
        while (true) {
            try {
                synchronized (this) {
                    wait();
                    System.out.println(i);
                }
                if (++n >= i) {
                    System.out.println("thread  " + getName() + " joined");
                    join();
                }
            }
            catch (InterruptedException e) {}
        }
    }
    
    public static void main(String args[]) {
        T[] ts = new T[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new T(i + 1);
            ts[i].start();
        }

        Random r = new Random();
        while (true) {
            try {
                Thread.sleep(250);
                T t = ts[r.nextInt(10)];
                t.play();
            }
            catch (InterruptedException e) {}
        }
    }
}
