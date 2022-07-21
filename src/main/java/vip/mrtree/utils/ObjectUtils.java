package vip.mrtree.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        }
        if (obj instanceof Map) {
            return MapUtils.isEmpty((Map<?, ?>) obj);
        }
        if (obj instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) obj);
        }
        return obj == null;
    }

    /**
     * 是否包含为空的对象
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean containEmpty(Object... obj) {
        for (Object o : obj) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }
}
