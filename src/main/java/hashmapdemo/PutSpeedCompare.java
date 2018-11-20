package hashmapdemo;

import java.util.HashMap;
import java.util.Map;

/**
 * 比较不初始化HashMap容量与初始化之间 速度的差别
 * 1 代表不初始化 2 代表初始化
 * 将两种情况一起测试数据与分开不同，
 * 在 count = 10000000 时， 耗时 2 > 1
 * 在 count =  1000000 时， 耗时 1 > 2 1 数值不稳定
 *
 * CPU和内存使用情况也有待测试
 *
 * 结论待定，但是'初始化'速度不一定就快，
 * @author ice
 * @date 18-11-1 下午6:20
 */
public class PutSpeedCompare {

    public static int count = 10000000;

    public static Long speedMap(boolean flag, int size) {
        Map map;
        if (flag) {
            map = new HashMap();
        } else {
            int initCap = (int) (size / 0.75) + 1;
            map = new HashMap(size);
        }
        Long beginTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        Long endTime = System.currentTimeMillis();
        Long time = endTime - beginTime;
        return time;
    }

    public static void main(String[] args) {
        //Long time1 = speedMap(false, count);
       // Long tim2 = speedMap(true, count);
        Long time1 = speedMap(true, count);
        System.out.println(time1);
    }
}
