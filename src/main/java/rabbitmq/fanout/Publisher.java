package rabbitmq.fanout;

import clonetest.ExampleEntity;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fanout 广播模式
 * 发布者
 * 发布者发布一条消息给名为’logs‘的exchange
 * exchange会将消息广播到已经bind到exchange的所有队列中
 * @author ice
 * @date 18-11-7 下午2:53
 */
public class Publisher {

    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args)
        throws IOException, TimeoutException {

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明一个名为EXCHANGE_NAME 的exchange，模式为fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello RabbitMQ publish/subscribe ";

        // 向名为EXCHANGE_NAME的exchange发送消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        channel.close();
        connection.close();
    }
}
