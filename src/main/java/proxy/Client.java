package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Java 的动态代理
 * 这里只是JDK代理方式的Demo,还有一种CGLIB方式进行代理
 * JDK方式，需要被代理的类实现一个接口，
 * CGLIB继承被代理的类，覆盖其方法
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
