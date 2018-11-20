package rabbitmq.fanout;

import com.rabbitmq.client.*;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅者,声明的队列为临时队列
 * 订阅者声明一个队列，并将此队列bind到名为‘logs’的exchange中
 * @author ice
 * @date 18-11-7 下午2:53
 */
public class Subscribe {

    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 用默认方式声明一个队列
        String queueName = channel.queueDeclare().getQueue();
        // 将此队列bind到EXCHANGE_NAME中
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("say: " + message);
            }
        };
        // 确认
        channel.basicConsume(queueName, true, consumer);
    }
}
