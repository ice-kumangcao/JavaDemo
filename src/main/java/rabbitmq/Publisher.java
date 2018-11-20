package rabbitmq;

import clonetest.ExampleEntity;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布者
 * @author ice
 * @date 18-11-7 下午2:53
 */
public class Publisher {

    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args)
        throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.17.0.5");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello RabbitMQ publish/subscribe ";

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        channel.close();
        connection.close();
    }
}
