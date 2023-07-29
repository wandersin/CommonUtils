package vip.mrtree.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_DATEFORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 获取标准格式日期字符串
     * <br>
     * like 2022/04/15 14:29:30
     * @author wangyunshu
     */
    public static String getDateStr() {
        return getDateStr(System.currentTimeMillis());
    }

    /**
     * 获取指定格式的日期字符串
     * <br>
     *
     * @param fmt 格式
     * @author wangyunshu
     */
    public static String getDateStr(String fmt) {
        return getDateStr(System.currentTimeMillis(), fmt);
    }

    /**
     * 获取指定格式的日期字符串
     * <br>
     *
     * @param time 毫秒数
     * @author wangyunshu
     */
    public static String getDateStr(long time) {
        return getDateStr(time, DateUtils.DEFAULT_DATEFORMAT);
    }

    /**
     * 获取指定格式的日期字符串
     * <br>
     *
     * @param time 毫秒数
     * @param fmt 格式
     * @author wangyunshu
     */
    public static String getDateStr(long time, String fmt) {
        DateFormat format = new SimpleDateFormat(fmt);
        return format.format(new Date(time));
    }

    /**
     * 获取指定日期
     * <br>
     *
     * @param time 毫秒数
     * @author wangyunshu
     */
    public static Date getDate(long time) {
        return new Date(time);
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

    public static String format(Date date) {
        return format(date, new SimpleDateFormat(DEFAULT_DATEFORMAT));
    }

    public static String format(String fmt) {
        return format(new Date(), new SimpleDateFormat(fmt));
    }

    public static String format(Date date, String fmt) {
        return format(date, new SimpleDateFormat(fmt));
    }

    public static String format(Date date, DateFormat fmt) {
        return fmt.format(date);
    }

    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_DATEFORMAT);
    }

    public static Date parse(String dateStr, String fmt) {
        try {
            return new SimpleDateFormat(fmt).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long calculate(String start, String end, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return calculate(fmt.parse(start), fmt.parse(end));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long calculate(Date start, Date end) {
        while (start.compareTo(end) > 0) { // start早于end, 就将end + 1天, 以实现跨天计算
            end = plusDay(end, 1);
        }
        return end.getTime() - start.getTime();
    }

    public static Date plusDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }
}
