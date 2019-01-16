package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ice
 * @date 18-12-27 上午11:23
 */
public class Test {

    public static void main(String[] args) {
        speedOfReflect();
    }

    /**
     * 获取Person的class 对象
     */
    public static void getClassObj() {
        Class clazz = Person.class;

        System.out.println(clazz.getClassLoader());

        System.out.println("直接通过类名得到：" + clazz);

        Object object = new Person();
        clazz = object.getClass();
        System.out.println("通过getClass: " + clazz);

        try {
            clazz = Class.forName("reflect.Person");
            System.out.println("通过全类名获取: " + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类的实例
     */
    public static void getClassInstance() {
        try {
            Class clazz = Class.forName("reflect.Person");
            System.out.println(clazz.getClassLoader());
            Object object = clazz.newInstance();
            System.out.println(object.toString());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void getClassMethods() {

        try {
            Class<?> clazz = Class.forName("reflect.Person");

            // 1. 得到clazz类中方法，不能获取private方法
            Method[] methods = clazz.getMethods();
            System.out.println("getMethods:");
            for (Method method : methods) {
                System.out.print(method.getName() + ", ");
            }
            System.out.println();

            // 2. 获取所有的方法（包括private方法）
            Method[] allMethods = clazz.getDeclaredMethods();
            System.out.println("getDeclareMethods:");
            for (Method method : allMethods) {
                System.out.print(method.getName() + ", ");
            }
            System.out.println();

            // 3. 获取指定的方法
            Method method = clazz.getDeclaredMethod("setName", String.class);
            System.out.println("method: " + method);


            Method method2 = clazz.getDeclaredMethod("setName",
                    String.class, int.class);
            System.out.println("method2: " + method2);

            // 4. 执行方法
            Object object = clazz.newInstance();
            method2.invoke(object, "zhangsan", 22);
            method.invoke(object, "zhangsan");
            System.out.println(object.toString());

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void getClassFields() {

        try {
            Class clazz = Class.forName("reflect.Person");

            // 1.获取class的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.print(field.getName() + ", ");
            }

            // 2. 获取指定名称的属性
            Field explicitField = clazz.getDeclaredField("name");
            System.out.println("\n获取指定Field名 = " + explicitField.getName());

            // 3. 获取对象中指定属性值
            Person person = new Person();
            person.setName("zhangsan");
            Object value = explicitField.get(person);
            System.out.println("获取Person实例中字段'name'的Field的值: " + value);

            // 4. 设置指定对象的Field的值
            explicitField.set(person, "lisi");
            System.out.println("设置指定对象字段'name'的Field的值 = " + person.getName());

            // 5. 若该字段是私有的，需要调用setAccessible(true)方法
            Field explicitField2= clazz.getDeclaredField("age");
            explicitField2.setAccessible(true);
            System.out.println("获取指定私有的字段名: " + explicitField2.get(person));

        } catch (ClassNotFoundException
                | NoSuchFieldException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void getSuperMethod() {

        try {
            Class<?> clazz = Class.forName("reflect.son");
            Class<?> superClazz = clazz.getSuperclass();
            System.out.println(superClazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void speedOfReflect() {

        int count = 10000000;
        long step0 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Person person = new Person();
            person.setName("zhangsan");
            person.getName();
        }
        long step1 = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            try {
                Class<?> explicitClass = Class.forName("reflect.Person");
                Method setNameMethod = explicitClass.
                        getDeclaredMethod("setName", String.class);
                Method getNameMethod = explicitClass.
                        getDeclaredMethod("getName");
                Object person = explicitClass.newInstance();
                setNameMethod.invoke(person, "zhangsan");
                getNameMethod.invoke(person);
            } catch (ClassNotFoundException
                    | NoSuchMethodException
                    | InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        long step2 = System.currentTimeMillis();

        System.out.println("不反射时间: " + (step1 - step0));
        System.out.println("反射时间: " + (step2 - step1));
    }
}
