package vip.mrtree.utils;

import java.util.Collection;

public class ArrayUtils {
    /**
     * 是否为空数组
     * <br>
     *
     * @author wangyunshu
     */
    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 是否为非空集合
     * <br>
     *
     * @author wangyunshu
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
