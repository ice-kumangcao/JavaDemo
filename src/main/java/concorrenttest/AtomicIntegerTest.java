package concorrenttest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ice
 * @date 19-2-14 下午3:02
 */
public class AtomicIntegerTest {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        Thread.sleep(500);
        System.out.println(runnable.get().get());
    }
}

class MyRunnable implements Runnable {

    private static AtomicInteger atomicInteger =
            new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            int result = atomicInteger.getAndIncrement();
            System.out.println(result);
        }
    }

    public AtomicInteger get() {
        return atomicInteger;
    }
}
