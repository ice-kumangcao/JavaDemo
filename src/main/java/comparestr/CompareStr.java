package comparestr;

/**
 * 比较字符串，进行排序
 * 比较字符串用compareTo函数
 * a.compareTo(b) return a-b
 * @author ice
 * @date 18-11-19 下午1:55
 */
public class CompareStr {

    /**
     * 用冒泡排序算法实现字符串的从小到大排序，
     * 直接改变原有字符串数组
     * @author ice
     * @param strings 需要排序的数组
     */
    public static void sortStringArray(String[] strings) {
        for (int i = 0;i < strings.length - 1; i++) {
            for (int j = 0;j < strings.length - i - 1; j++) {
                if (strings[j].compareTo(strings[j + 1]) > 0) {
                    String tmp = strings[j + 1];
                    strings[j + 1] = strings[j];
                    strings[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {

        String[] strings = new String[] {"生物", "物理", "化学", "地理", "政治", "历史"};

        sortStringArray(strings);

        for (String item : strings) {
            System.out.println(item);
        }
    }
}
