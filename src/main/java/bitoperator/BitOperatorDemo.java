package bitoperator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查看-1L的二进制表示
 * @author ice
 * @date 19-2-25 下午3:35
 */
public class BitOperatorDemo {
    public static void main(String[] args) {
        long value = -1L;
        System.out.println(Long.toBinaryString(value));

        Date date = new Date(0L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println(dateStr);
    }
}
