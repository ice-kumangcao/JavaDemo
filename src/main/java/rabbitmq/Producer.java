package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ice
 * @date 18-11-6 下午8:26
 */
public class Producer {

    public final static String QUEUE_NAME = "test";

    public static void  main(String[] args) throws TimeoutException, IOException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("172.17.0.4");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String message = "Hello RabbitMQ";
        //发送消息到队列中
        for (int i = 0;i < 10; i++) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        }
        System.out.println("Producer Send +'" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
