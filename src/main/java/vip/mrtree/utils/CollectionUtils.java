package vip.mrtree.utils;

import java.util.Collection;
import java.util.List;

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
    public static <T> String join(List<T> list, String symbol) {
        if (isEmpty(list)) {
            return "";
        }
        String temp = symbol == null ? "" : symbol;
        StringBuilder builder = new StringBuilder(String.valueOf(list.get(0)));
        for (int i = 1; i < list.size(); i++) {
            builder.append(temp);
            builder.append(list.get(i));
        }
        return builder.toString();
    }
}
