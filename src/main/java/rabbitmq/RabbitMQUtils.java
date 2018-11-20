package rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 连接rabbitMQ工具类
 * @author ice
 * @date 18-11-8 下午2:27
 */
public class RabbitMQUtils {

    private static String IP = "172.17.0.5";

    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null || !connection.isOpen()) {
            // 创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            // 设置IP
            factory.setHost(IP);
            try {
                connection = factory.newConnection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
