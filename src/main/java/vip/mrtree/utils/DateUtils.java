package vip.mrtree.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_DATEFORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 获取标准格式日期
     * <br>
     * like 2022/04/15 14:29:30
     * @author wangyunshu
     */
    public static String getDate() {
        return getDate(DateUtils.DEFAULT_DATEFORMAT);
    }

    /**
     * 获取指定格式的日期
     * <br>
     *
     * @author wangyunshu
     */
    public static String getDate(String fmt) {
        DateFormat format = new SimpleDateFormat(fmt);
        return format.format(new Date());
    }

    /**
     * 获取当前时间戳
     * <br>
     *
     * @author wangyunshu
     */
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
