package classloader;

import java.io.*;

/**
 * 实现自己的ClassLoader加载.class文件
 * @author ice
 * @date 18-12-25 上午9:53
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 类的加载路径
     */
    private String path;

    /**
     * 类加载器的名字
     */
    private String name;

    public MyClassLoader() {}

    public MyClassLoader(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {

        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        File file = new File(name);
        System.out.println("read " + file.getAbsolutePath());
        // 读.class文件输出字节数据
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return out.toByteArray();
    }

    public String getName() {
        return name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
