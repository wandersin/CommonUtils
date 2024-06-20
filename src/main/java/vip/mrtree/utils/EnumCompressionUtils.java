package vip.mrtree.utils;

import vip.mrtree.component.EnumCompressionService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 枚举类型压缩存储工具类
 * <br>
 * 压缩存储举例:
 * 假设现有三种特性A, B, C, 可以将三种特性分别记作0b1, 0b10, 0b100, 针对不断增长的特性D, E, F..., 可继续定义0b1000, 0b10000, 0b100000...
 * 对象1拥有AC两种特性, 可以记作0b101
 * 对象2拥有ABC三种特性, 可以记作0b111
 * 对象3拥有新增的B, D, E...特性, 可以记作0bxx...010
 * <p>
 * 在存储时, 只需要一个int字段即可保存对象拥有的所有特性, 并且可以兼容特性不断增加的场景
 * @author wangyunshu
 */
public class EnumCompressionUtils {
    /**
     * 将特性列表转为存储需要的int值
     * <br>
     *
     * @author wangyunshu
     */
    public static int enum2int(Collection<? extends EnumCompressionService> list) {
        int result = 0;
        for (EnumCompressionService item : list) {
            result += item.getValue();
        }
        return result;
    }

    /**
     * 将存储的int值转换为特性列表
     * <br>
     *
     * @author wangyunshu
     */
    public static <T extends EnumCompressionService> Collection<T> int2enum(int value, Class<T> clazz) {
        Collection<T> result = new ArrayList<>();
        T[] array = clazz.getEnumConstants();
        for (T t : array) {
            if ((value & t.getValue()) == t.getValue()) {
                result.add(t);
            }
        }
        return result;
    }
}
