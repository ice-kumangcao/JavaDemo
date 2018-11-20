package rabbitmq.fanout;

import com.rabbitmq.client.*;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.Queue;

/**
 * 订阅者，声明的队列为持久化队列
 * @author ice
 * @date 18-11-20 下午1:06
 */
public class Subscribe2 {

    private static String QUEUE_NAME = "subscribe2";

    private static String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException{

        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明名为QUEUE_NAME的持久化队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 将队列bind到exchange中
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("say: " + message);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
