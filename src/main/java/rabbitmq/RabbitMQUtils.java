package rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ice
 * @date 18-11-8 下午2:27
 */
public class RabbitMQUtils {

    public static Connection connection;
    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.17.0.5");
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
