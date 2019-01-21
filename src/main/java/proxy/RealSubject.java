package proxy;

/**
 * @author ice
 * @date 19-1-17 下午4:12
 */
public class RealSubject implements Subject {

    @Override
    public void name() {
        System.out.println("My name is ...");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
