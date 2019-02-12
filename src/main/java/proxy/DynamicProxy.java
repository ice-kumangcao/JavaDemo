package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ice
 * @date 19-1-17 下午4:14
 */
public class DynamicProxy implements InvocationHandler {

    private Object subject;

    DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("do something before");
        System.out.println("method: " + method);
        // 将subject改为proxy,会形成闭环
        method.invoke(subject, args);
        System.out.println("do something after");
        return null;
    }
}
