package rpc.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 中间件应该提供的消费工具
 * @author ice
 * @date 19-3-8 下午3:08
 */
public class RPCClient<T> {

    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface,
                                          final InetSocketAddress addr) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try {
                            socket = new Socket();
                            socket.connect(addr);

                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceInterface.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);

                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } finally {
                            if (socket != null) {
                                socket.close();
                            }
                            if (output != null) {
                                output.close();
                            }
                            if (input != null) {
                                input.close();
                            }
                        }
                    }
                });
    }
}
