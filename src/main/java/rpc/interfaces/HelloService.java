package rpc.interfaces;

/**
 * 生产者和消费者统一的接口
 * @author ice
 * @date 19-3-8 上午10:43
 */
public interface HelloService {

    /**
     * say hi
     * @param name name
     * @return 'hi' + name
     */
    String sayHi(String name);
}
