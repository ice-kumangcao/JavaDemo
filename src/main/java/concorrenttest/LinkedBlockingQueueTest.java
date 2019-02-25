package concorrenttest;

import sun.reflect.FieldAccessor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ice
 * @date 19-2-14 下午5:02
 */
public class LinkedBlockingQueueTest {

    private int i;
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);

    class Producer implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    queue.put(i++);
                    System.out.println("生产 剩余容量" + queue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("消费 剩余容量" + queue.remainingCapacity());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueueTest linkedBlockingQueueTest =
                new LinkedBlockingQueueTest();
        new Thread(linkedBlockingQueueTest.new Producer()).start();
        new Thread(linkedBlockingQueueTest.new Consumer()).start();
    }
}
