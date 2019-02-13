package locktest;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁一个简单的Demo
 * @author ice
 * @date 18-12-27 上午8:52
 */
public class ReadWriteLockTest {

    private final Entity entity = new Entity(1);

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        readWriteLockTest.test();
    }

    public void test() {
        for(int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    entity.get();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    entity.set();
                }
            }).start();
        }
    }

    class Entity {

        private Object data = null;

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Entity(Object data) {
            this.data = data;
        }

        public void get() {
            readWriteLock.readLock().lock();

            try {
                System.out.print("\n" + Thread.currentThread().getName() + " 正在读取数据 ");

                Thread.sleep((long)(Math.random() * 1000));
                System.out.print(Thread.currentThread().getName() + " 读出数据为：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

        public void set() {
            readWriteLock.writeLock().lock();

            try {
                System.out.print("\n" + Thread.currentThread().getName() + " 正在写数据 ");

                Thread.sleep((long)(Math.random() * 1000));
                data = Integer.parseInt(this.data.toString()) + 1;
                System.out.print(Thread.currentThread().getName() + "写入数据为：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }
}
