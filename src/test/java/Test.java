import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ice
 * @date 18-12-27 下午5:04
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        List<String> list1 = Test.get(list);

        if (list == list1) {
            System.out.println("list");
        }

        Map<String, String> map = new HashMap<>();
        map.put("zhangsan", "1");

        Map<String, String> map1 = Test.get(map);

        if (map == map1) {
            System.out.println("map");
        }
    }

    public static  <T> T get(Object object) {
        return (T) object;
    }
}
