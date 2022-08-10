package vip.mrtree.utils;

import java.util.Collection;

public class CollectionUtils {
    /**
     * 是否为空集合
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 是否为非空集合
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 连接集合
     * <br>
     *
     * @author wangyunshu
     */
    public static <T> String join(Collection<T> list, String _symbol) {
        if (isEmpty(list)) {
            return "";
        }
        String symbol = _symbol == null ? "" : _symbol;
        StringBuilder builder = new StringBuilder();
        list.forEach(item -> {
            builder.append(symbol);
            builder.append(item);
        });
        return builder.substring(symbol.length());
    }
}
