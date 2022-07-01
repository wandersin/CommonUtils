package vip.mrtree.utils;

public class NumberUtils {
    /**
     * 判断对象是否为数字
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断obj是否不是数字
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isNotNumber(Object obj) {
        return !isNumber(obj);
    }
}
