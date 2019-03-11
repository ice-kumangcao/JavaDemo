package rpc.producer;

import rpc.interfaces.HelloService;

/**
 * 生产者
 * @author ice
 * @date 19-3-8 上午10:44
 */
public class HelloServiceImpl implements HelloService {

    /**
     * say hi
     *
     * @param name name
     * @return 'hi' + name
     */
    @Override
    public String sayHi(String name) {
        return "hi " + name;
    }
}
