package rpc.producer;

import rpc.interfaces.HelloService;
import rpc.middleware.Server;
import rpc.middleware.ServiceCenter;
import rpc.producer.HelloServiceImpl;

import java.io.IOException;

/**
 * 中间件应该提供的，服务中心启动和注册生产者的类
 * @author ice
 * @date 19-3-8 下午3:42
 */
public class ServerTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
