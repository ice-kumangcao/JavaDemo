package rpc.middleware;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 中间件服务中心实现，统一管理生产者
 * @author ice
 * @date 19-3-8 上午10:47
 */
public class ServiceCenter implements Server {

    private static ExecutorService executor =
            Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors());

    private static final HashMap<String, Class> SERVICE_REGISTRY = new HashMap<>();

    private static boolean IS_RUNNING = false;

    private static int PORT;

    public ServiceCenter(int port) {
        PORT  = port;
    }

    /**
     * 停止服务
     */
    @Override
    public void stop() {
        IS_RUNNING = false;
        executor.shutdown();
    }

    /**
     * 启动服务
     *
     * @throws IOException io异常
     */
    @Override
    public void start() throws IOException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(PORT));
        System.out.println("start server");
        try {
            while (true) {
                executor.execute(new ServiceTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    /**
     * 注册服务
     *
     * @param serviceInterface 接口
     * @param impl             实现
     */
    @Override
    public void register(Class<?> serviceInterface, Class impl) {

        SERVICE_REGISTRY.put(serviceInterface.getName(), impl);
    }

    /**
     * 是否运行
     *
     * @return 运行true，停止false
     */
    @Override
    public boolean isRunning() {
        return IS_RUNNING;
    }

    /**
     * 得到端口
     *
     * @return 端口号
     */
    @Override
    public int getPort() {
        return PORT;
    }

    private static class ServiceTask implements Runnable {

        Socket client;

        ServiceTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;

            try {
                input = new ObjectInputStream(client.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();

                Class<?> serviceClass = SERVICE_REGISTRY.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + "not found");
                }

                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);

            } catch (IOException
                    | ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | NoSuchMethodException
                    | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (client != null) {
                    try{
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
