package vip.mrtree.utils;

import vip.mrtree.component.EnumCompressionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
     * 支持定义一个value为负数的特殊元素
     * 比如: 可以在enum中定一个一个value为-1的EMPYT, NULL, ALL...等需要特殊处理的元素
     * 当list中包含该特殊元素时, 返回结果为负数(该特殊元素的value)
     * 注: 原则上每个enum只支持一个value为负数的特殊元素
     * @author wangyunshu
     */
    public static int enum2int(Collection<? extends EnumCompressionService> list) {
        int result = 0;
        for (EnumCompressionService item : list) {
            if (item.getValue() < 0) {
                // 特殊值
                return item.getValue();
            }
            result += item.getValue();
        }
        return result;
    }

    /**
     * 将存储的int值转换为特性列表
     * <br>
     * 支持定义一个value为负数的特殊元素
     * 比如: 可以在enum中定一个一个value为-1的EMPYT, NULL, ALL...等需要特殊处理的元素
     * 当入参为负时, 检查是否为特殊元素, 如果是, 则结果只返回特殊元素; 如果不是, 则返回空列表
     * @author wangyunshu
     */
    public static <T extends EnumCompressionService> Collection<T> int2enum(int value, Class<T> clazz) {
        T[] array = clazz.getEnumConstants();
        if (value < 0) {
            // 特殊元素处理
            for (T t : array) {
                if (t.getValue() == value) {
                    return Collections.singleton(t);
                }
            }
            return Collections.emptyList();
        }
        Collection<T> result = new ArrayList<>();
        for (T t : array) {
            if ((value & t.getValue()) == t.getValue()) {
                result.add(t);
            }
        }
        return result;
    }
}
