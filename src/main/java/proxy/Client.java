package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ice
 * @date 19-1-17 下午4:17
 */
public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(realSubject);
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
        System.out.println(subject.getClass().getName());
        subject.name();
        subject.hello("world");
    }
}
