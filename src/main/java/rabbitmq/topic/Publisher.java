package rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ice
 * @date 18-11-8 下午2:25
 */
public class Publisher {

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args)
            throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.connection;

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "hello ....";

        String routingKey = "goods.add";
        channel.basicPublish(EXCHANGE_NAME, routingKey,
                null,message.getBytes());
        System.out.println("send " + message);

        channel.close();
        connection.close();
    }
}
