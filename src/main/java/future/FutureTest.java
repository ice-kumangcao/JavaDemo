package future;

import java.util.concurrent.*;

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
        usage3();
    }

    /**
     * future 用法1
     * 用线程池运行FutureTask
     */
    public static void usage1() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(4000);
                return Thread.currentThread().getName();
            }
        });
        executor.submit(future);
        executor.shutdown();

        try {
            Thread.sleep(4000);
            System.out.println("thread should run over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            String result = future.get(5000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用Thread运行FutureTask
     */
    public static void usage2() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return Thread.currentThread().getName();
            }
        });
        Thread thread = new Thread(futureTask);
        // 一定要start，不start futureTask就没有作用了
        // futureTask监控运行状态
        thread.start();
        try {
            Thread.sleep(2000);
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void usage3() {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        String result = "new";
        FutureTask<String> futureTask = new FutureTask<>(runnable, result);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            String value = futureTask.get();
            System.out.println(value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
