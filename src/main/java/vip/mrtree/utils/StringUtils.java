package vip.mrtree.utils;

public class StringUtils {
    /**
     * 字符串是否为空
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 字符串是否为空
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
}
