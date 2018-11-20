package rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者生产10条message放到名为‘test’队列中
 * ‘test’队列为持久化队列
 * @author ice
 * @date 18-11-6 下午8:26
 */
public class Producer {

    public final static String QUEUE_NAME = "test";

    public static void  main(String[] args) throws TimeoutException, IOException {

        Connection connection = RabbitMQUtils.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列
        // 第二个参数是控制是否为持久化队列，
        // 持久化队列是一直保存，即使是服务重启，也会保存下来
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
