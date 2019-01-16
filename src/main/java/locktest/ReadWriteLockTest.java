package locktest;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ice
 * @date 18-12-27 上午8:52
 */
public class ReadWriteLockTest {

    final Entity entity = new Entity();

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
                    entity.set(new Random().nextInt(8));
                }
            }).start();
        }
    }

    class Entity {

        private Object data = null;

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void get() {
            readWriteLock.readLock().lock();

            try {
                System.out.print(Thread.currentThread().getName() + " 正在读取数据 ");

                Thread.sleep((long)(Math.random() * 1000));
                System.out.print(Thread.currentThread().getName() + " 读出数据为：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

        public void set(Object data) {
            readWriteLock.writeLock().lock();

            try {
                System.out.print("\n" + Thread.currentThread().getName() + " 正在写数据 " + "\n");

                Thread.sleep((long)(Math.random() * 1000));
                this.data = data;
                System.out.print("\n" + Thread.currentThread().getName() + "写入数据为：" + data + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }
}
