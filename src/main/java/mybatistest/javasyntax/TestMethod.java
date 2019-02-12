package mybatistest.javasyntax;

import classloader.Test;
import mybatistest.OneEntity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ice
 * @date 19-1-21 下午1:42
 */
public class TestMethod {
    public static void main(String[] args) {
        OneEntity oneEntity = new OneEntity();
        Class<?> clazz = oneEntity.getClass();
        try {
            Method onEntityMethod = clazz.getDeclaredMethod("getName");
            Method objectMetod = Object.class.getDeclaredMethod("toString");
            if (Object.class.equals(onEntityMethod.getDeclaringClass())) {
                System.out.println("getName is Object declare");
            }
            if (Object.class.equals(objectMetod.getDeclaringClass())) {
                System.out.println("toString is Object declare");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        List<String> strings = TestMethod.getList();
        for (String string : strings) {
            System.out.println(string);
        }
    }


    public static <E> List<E> getList() {

        return TestMethod.<E> getList1();
    }

    public static  <E> List<E> getList1() {
        String str = "zhangsan";
        List<E> list = new ArrayList<>();
        list.add((E) str);
        return list;
    }
}
