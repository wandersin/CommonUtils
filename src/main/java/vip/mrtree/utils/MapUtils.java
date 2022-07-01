package vip.mrtree.utils;

import java.util.Map;

public class MapUtils {
    /**
     * Map是否为空
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Map是否为空
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
