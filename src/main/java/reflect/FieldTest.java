package reflect;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 1.通过反射修改DATA值，两种方法获取的值不同
 *   直接获取和通过反射获取值不同，猜测涉及到JVM存储问题，没有深究
 *   //TODO: JVM内存管理
 * 2.如果将DATA = new String()，两种方法获取的值一致
 * @author ice
 * @date 19-2-15 上午10:52
 */
public class FieldTest {

    static final String DATA = "hello world";

    public static void main(String[] args) {

        FieldTest fieldTest = new FieldTest();

        try {
            Field field = FieldTest.class.getField("DATA");

            // 修改final描述
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() &~ Modifier.FINAL);

            // 修改值
            field.set(fieldTest, "change");

            // 普通直接获取
            System.out.println(fieldTest.DATA);

            // 通过反射获取值
            String reflectFieldValue = field.get(fieldTest).toString();
            System.out.println("reflect field value:" + reflectFieldValue);


            ReflectionFactory reflectionFac = ReflectionFactory.getReflectionFactory();
            reflectionFac.newFieldAccessor(field, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
