package classloader;

/**
 * @author ice
 * @date 18-12-25 上午10:00
 */
public class Test {
    public static void main(String[] args) {
        MyClassLoader m = new MyClassLoader(
                "src/main/java/classloader/",
                "TestEntity");

        Class c = m.findClass("TestEntity");
        System.out.println(c.getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Test.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        System.out.println("Thread's ClassLoader " +
                Thread.class.getClassLoader());
    }
}
