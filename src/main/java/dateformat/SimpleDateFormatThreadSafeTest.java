package dateformat;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * SimpleDateFormat 线程不安全的测试
 * 微信公众号 Hollis 中的一篇文章
 * @author ice
 * @date 18-12-6 下午1:29
 */
public class SimpleDateFormatThreadSafeTest {

    /**
     * 定义全局SimpleDateFormat
     */
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 使用ThreadFactoryBuilder定义一个线程池
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 定义一个CountDownLatch
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException{

        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                synchronized (simpleDateFormat) {
                    calendar.add(Calendar.DATE, finalI);
                    String dateString = simpleDateFormat.format(calendar.getTime());
                    dates.add(dateString);
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        pool.shutdown();
        System.out.println(dates.size());
    }
}
