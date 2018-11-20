package threadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ice
 * @date 18-11-14 上午9:02
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
        singleThreadExecutor.shutdown();
        System.out.println("over");
    }
}
