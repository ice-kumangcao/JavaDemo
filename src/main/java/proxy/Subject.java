package proxy;

/**
 * @author ice
 * @date 19-1-17 下午4:12
 */
public interface Subject {

    /**
     * 输出name
     */
    void name();

    /**
     * 输出hello + str
     * @param str 字符串
     */
    void hello(String str);
}
