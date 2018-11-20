package rabbitmq.topic;

import com.rabbitmq.client.*;
import rabbitmq.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ice
 * @date 18-11-8 下午2:41
 */
public class Subscribe1 {

    private static final String QUEUE_NAME = "queue_topic_one";
    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] args)
        throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.connection;

        //从连接中获取频道
        final Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String routingKey = "goods.add";
        //绑定队列到交换机 转发器
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingKey);

        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] Recv msg:" + msg);

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
