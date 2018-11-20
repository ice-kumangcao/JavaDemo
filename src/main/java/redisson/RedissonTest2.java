package redisson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author ice
 * @date 18-11-2 下午3:52
 */
public class RedissonTest2 {

    private static int COUNT = 1000;
    public void run() {

        final CountDownLatch startSign = new CountDownLatch(1);
        final CountDownLatch endSign = new CountDownLatch(COUNT);

        // 初始化HashMap
        final Map<String, Integer> map = new HashMap(8);
        for (int i = 0; i < 4; i++) {
            int remainder = i % 4;
            map.put(remainder + "", 0);
        }
        for (int i = 0; i < COUNT; i++) {
            int remainder = i % 4;
            int count = i % 100;
            Thread thread = new Thread(new Worker(startSign, endSign, map, remainder + ""));
            thread.start();
        }
        // begin
        startSign.countDown();
        System.out.println("all thread begin");
        try {
            // end
            endSign.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all thread end");
        for (Map.Entry entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch endSignal;
        private final Map<String, Integer> map;
        private final String id;
        Worker(CountDownLatch startSignal, CountDownLatch endSignal, Map map, String id) {
            this.startSignal = startSignal;
            this.endSignal = endSignal;
            this.map = map;
            this.id = id;
        }
        public void run() {
            try {
                startSignal.await();
                int result;
                int value = map.get(id);
                result = value + 1;
                map.put(id, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                endSignal.countDown();
            }
        }
    }
    public static void main(String[] args) {
        RedissonTest2 redissonTest = new RedissonTest2();
        redissonTest.run();
    }
}
