package redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * redis 分布式工具 redisson 使用
 * HashMap 非线程安全
 * @author ice
 */
public class RedissonTest {

    private static int COUNT = 10000;
    public void run() {

        final CountDownLatch startSign = new CountDownLatch(1);
        final CountDownLatch endSign = new CountDownLatch(COUNT);

        // redisson 连接 redis服务器
        Config config = new Config();
        config.useSingleServer().setAddress("172.17.0.4:6379");
        RedissonClient redissonClient = Redisson.create(config);

        // 初始化HashMap
        final Map<String, Integer> map = new HashMap(8);
        for (int i = 0; i < 4; i++) {
            int remainder = i % 4;
            map.put(remainder + "", 0);
        }
        for (int i = 0; i < COUNT; i++) {
            int remainder = i % 4;
            int count = i % 100;
            // get Lock
            final RLock rLock = redissonClient.getLock(remainder + "");
            Thread thread = new Thread(new Worker(redissonClient, startSign, endSign, map, remainder + ""));
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
        redissonClient.shutdown();
    }
    class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final RLock rLock;
        private final CountDownLatch endSignal;
        private final Map<String, Integer> map;
        private final String id;
        Worker(RedissonClient redissonClient, CountDownLatch startSignal, CountDownLatch endSignal, Map map, String id) {
            rLock = redissonClient.getLock(id);
            this.startSignal = startSignal;
            this.endSignal = endSignal;
            this.map = map;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                // do work
                // 设置锁的有效时间，加锁
                rLock.tryLock(1, 10, TimeUnit.SECONDS);
                int result;
                int value = map.get(id);
                result = value + 1;
                map.put(id, result);
                // 解锁
                unlock(rLock);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                endSignal.countDown();
            }
        }

        private void unlock(RLock rLock) {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        RedissonTest redissonTest = new RedissonTest();
        redissonTest.run();
    }
}
