package rpc.middleware;

import java.io.IOException;

/**
 * 中间件的服务接口
 * @author ice
 * @date 19-3-8 上午10:46
 */
public interface Server {

    /**
     * 停止服务
     */
    void stop();

    /**
     * 启动服务
     * @throws IOException io异常
     */
    void start() throws IOException;

    /**
     * 注册服务
     * @param serviceInterface 接口
     * @param impl 实现
     */
    void register(Class<?> serviceInterface, Class impl);

    /**
     * 是否运行
     * @return 运行true，停止false
     */
    boolean isRunning();

    /**
     * 得到端口
     * @return 端口号
     */
    int getPort();
}
