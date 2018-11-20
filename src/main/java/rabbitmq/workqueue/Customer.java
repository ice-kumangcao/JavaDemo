package rabbitmq.workqueue;

import com.rabbitmq.client.*;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者关注‘test’队列
 * 如果有多个消费者，消息会均匀的发给所有消费者
 * 如果只有一个消费者，消息会全部发给它
 *
 * js实现消费者的代码在resources/rabbitmq/index.html中
 * @author ice
 * @date 18-11-6 下午8:33
 */
public class Customer {

    private final static String QUEUE_NAME = "test";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMQUtils.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("Customer Waiting Received messages");
        // DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
