package thinkinjava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ice
 * @date 19-1-7 下午1:26
 */
public class AnnotationTest {

    @Deprecated
    public void get() {

    }

    @SuppressWarnings(value = "unchecked")
    public void set() {
    }

    @Run void run() {
        System.out.println(this.getClass());
    }

    public static void main(String[] args) {
        AnnotationTest test = new AnnotationTest();
        Class<?> clazz = test.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            Run run = method.getAnnotation(Run.class);
            int parameterCount = method.getParameterCount();
            if (run != null && parameterCount == 0) {
                try {
                    method.invoke(test);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
