package rabbitmq.topic;

import com.rabbitmq.client.*;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ice
 * @date 18-11-8 下午2:41
 */
public class Subscribe {

    private static final String QUEUE_NAME = "queue_topic_one";
    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args)
        throws IOException {
        Connection connection = RabbitMQUtils.getConnection();

        //从连接中获取频道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String routingKey = "goods.#";
        //绑定队列到交换机 转发器
        // 定义匹配route key
        // 只有匹配route key 的消息才会被发送到此队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingKey);


        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] Recv msg:" + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME,true, consumer);
    }
}
