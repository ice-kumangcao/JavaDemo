package future;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * Callable 和 Runnable
 * Callable有返回值T, Runnable没有返回值
 *
 * Future/FutureTask 只是用来保存结果，查询运行状态的方法
 * @author ice
 * @date 19-2-11 上午8:52
 */
public class FutureTest {
    public static void main(String[] args) {
        usage1();
    }

    /**
     * future 用法1
     * 用线程池运行FutureTask 或者 用Thread
     */
    public static void usage1() {

        // 开辟一个线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 定义一个FutureTask
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(4000);
                return Thread.currentThread().getName();
            }
        });
        // 此时开始运行这个FutureTask,
        executor.submit(future);
        executor.shutdown();

        try {
            // 从FutureTask中获取结果，等待5000ms,如果超时则 throw TimeoutException
            // 此时线程可能还在运行，也可能已经运行成功，所以需要设置等待时间
            String result = future.get(5000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            // 此方法是一直等到返回结果，或者抛出异常为止
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void usage2() {

        // 用lambda表达式初始化一个FutureTask
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return Thread.currentThread().getName();
        });

        // 创建线程来运行FutureTask
        Thread thread = new Thread(futureTask);
        // 线程运行，开始运行FutureTask
        // 如果注释掉此句，主线程会一直park
        thread.start();

        try {
            // get()方法，会park当前线程，直到FutureTask中run方法被执行完成
            String value = futureTask.get();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * LockSupport 挂起线程与恢复线程
     */
    public static void usage3() {
        Runnable runnable = () -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " over");
        };
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("unpark thread");
        LockSupport.unpark(thread);

        //System.gc();
        Thread.yield();
        System.out.println("over");
    }
}
