package concorrenttest;

import sun.reflect.FieldAccessor;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ice
 * @date 19-2-14 下午3:15
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for (int i = 0; i < 100; i++) {
            System.out.print(i + " ");
            reentrantLockTest.test();
        }
    }

    /**
     * 线程开始
     */
    CountDownLatch begin = new CountDownLatch(1);

    /**
     * 线程结束
     */
    CountDownLatch end = new CountDownLatch(10000);

    public  void test() throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(myRunnable);
            thread.start();
        }
        // 开始运行所有线程
        begin.countDown();
        // 等待所有线程运行完成
        end.await();
        System.out.println("end result = " + myRunnable.getCount());

        // 还原计数器
        begin = new CountDownLatch(1);
        end = new CountDownLatch(10000);

    }

    class MyRunnable implements Runnable {

        private final ReentrantLock lock = new ReentrantLock();

        private int count = 0;

        public int getCount() {
            return count;
        }

        @Override
        public void run() {
            try {
                begin.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            int cacheCount = count;

            try {
                // 为了增大误差
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count = cacheCount + 1;
            lock.unlock();
            end.countDown();
        }


    }

}

