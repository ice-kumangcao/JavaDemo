package threadcount;

import java.util.concurrent.CountDownLatch;

/**
 * 就像百米赛跑一样
 * startSignal 就像 发令枪
 * doneSignal 就像 结束计时一样
 * oracle 官网关于CountDownLatch使用例子
 *
 * java.util.concurrent.CountDownLatch 使用
 * @author ice
 * @date 18-10-31 下午6:41
 */
public class Driver {

    class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }
        public void run() {
            try {
                startSignal.await();
                // do something
            } catch (InterruptedException e) {
                // do something
            }
            finally {
                // 与oracle demo 不同，
                // ali-check 提示 countDown()应放到finally中，防止计数错误
                doneSignal.countDown();
            }
        }
    }

    void main() throws InterruptedException {

        // n为线程数
        int n = 16;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        // run all
        startSignal.countDown();
        // wait for all to finish
        doneSignal.await();
    }
}
