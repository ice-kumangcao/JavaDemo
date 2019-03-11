package rpc.consumer;

import rpc.interfaces.HelloService;

import java.net.InetSocketAddress;

/**
 * 消费者
 * @author ice
 * @date 19-3-8 下午3:37
 */
public class RPCTest {

    public static void main(String[] args) {
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class,
                new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("world"));

        System.out.println(service.sayHi("kitty"));
    }
}
