package reflect;

import java.lang.reflect.Method;

/**
 * @author ice
 * @date 19-1-16 下午2:15
 */
public class ReflectSpeed {
    public static void main(String[] args) {
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Person person = new Person();
            person.setName("zhangsan");

        }
        Long end = System.currentTimeMillis();
        System.out.println(end - begin);

        Long beginReflect = System.currentTimeMillis();

        try {
            for (int i = 0; i < 500000; i++) {
                Class<?> clazz = Person.class;
                Object object = clazz.newInstance();
                Method method = clazz.getDeclaredMethod("setName", String.class);
                method.invoke(object, "zhangsan");
            }
            Long endReflect = System.currentTimeMillis();
            System.out.println(endReflect - beginReflect);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
