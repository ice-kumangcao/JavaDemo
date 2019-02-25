package threadlocal;

/**
 * ThreadLocal 虽然是static修饰的属性，但是线程之间ThreadLocal不共享
 * 所以ThreadLocal 是线程内静态变量
 * @author ice
 * @date 19-2-25 上午10:13
 */
public class ThreadLocalDemo {
    static class Resource {
        final static ThreadLocal<String> RESOURCE1 = new ThreadLocal<>();
        final static ThreadLocal<String> RESOURCE2 = new ThreadLocal<>();
    }

    static class A {
        void setFirst(String value) {
            Resource.RESOURCE1.set(value);
        }

        void setSecond(String value) {
            Resource.RESOURCE2.set(value);
        }
    }

    static class B {
        void display() {
            System.out.println(Resource.RESOURCE1.get()
                    + ":"
                    + Resource.RESOURCE2.get());
        }
    }

    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        for (int i = 0;i < 15; i++) {
            final String resource1 = "线程-" + i;
            final String resource2 = " value = (" + i + ")";
            new Thread(() -> {
                try {
                    b.display();
                    a.setFirst(resource1);
                    a.setSecond(resource2);
                    b.display();
                } finally {
                    Resource.RESOURCE1.remove();
                    Resource.RESOURCE2.remove();
                }
            }).start();
        }
    }
}
