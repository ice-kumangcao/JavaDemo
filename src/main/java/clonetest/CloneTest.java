package clonetest;

import java.util.ArrayList;
import java.util.List;

/**
 * clone
 * @author ice
 * @date 18-11-5 上午10:14
 */
public class CloneTest implements Cloneable{

    public static void main(String[] args) {

        ExampleEntity example = new ExampleEntity();
        example.setName("zhangsan");
        try {
            // clone一个example的克隆
            // 深克隆还是浅克隆看clone实现
            ExampleEntity exampleClone = (ExampleEntity) example.clone();
            example.setName("lisi");
            System.out.println("clone:" + exampleClone.getName());
            System.out.println("" + example.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
