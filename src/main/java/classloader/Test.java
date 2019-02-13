package classloader;

/**
 * 查看各类的加载器
 * @author ice
 * @date 18-12-25 上午10:00
 */
public class Test {
    public static void main(String[] args) {

        MyClassLoader m = new MyClassLoader(
                "src/main/java/classloader/",
                "TestEntity");
        // 加载TestEntity类
        Class c = m.findClass("TestEntity");
        System.out.println(c.getClassLoader());

        // 当前线程的默认类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Test.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        // 默认类加载器
        System.out.println(ClassLoader.getSystemClassLoader());
        // 父类加载器
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        // 父父类加载器
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        // Thread类的类加载器
        System.out.println("Thread's ClassLoader " +
                Thread.class.getClassLoader());
    }
}
