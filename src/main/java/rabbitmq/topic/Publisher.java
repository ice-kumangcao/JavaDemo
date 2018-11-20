package rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * exchange向bind的队列发送消息的三种模式
 * topic 发送到匹配route key的队列中
 * fanout 发送到所有队列中
 * direct 发送到制定route key的队列中
 * @author ice
 * @date 18-11-8 下午2:25
 */
public class Publisher {

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args)
            throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "hello ....";

        String routingKey = "goods.add";
        // 发送一条消息到exchange中
        channel.basicPublish(EXCHANGE_NAME, routingKey,
                null,message.getBytes());
        System.out.println("send " + message);

        channel.close();
        connection.close();
    }
}
