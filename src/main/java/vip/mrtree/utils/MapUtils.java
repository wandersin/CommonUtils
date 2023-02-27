package vip.mrtree.utils;

import java.util.ArrayList;
import java.util.Collection;
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

    public static String join(Map<?, ?> map, String _symbol) {
        if (isEmpty(map)) {
            return "";
        }
        Collection<String> tmp = new ArrayList<>();
        map.forEach((k, v) -> tmp.add(StringUtils.join(StringUtils.trim(k.toString()), "=", StringUtils.trim(v.toString()))));
        return CollectionUtils.join(tmp, _symbol);
    }
}
