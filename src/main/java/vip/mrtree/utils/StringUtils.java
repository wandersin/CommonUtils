package vip.mrtree.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Locale;
import java.util.Map;

public class StringUtils {
    /**
     * 字符串是否为空
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.isEmpty();
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

    /**
     * 字符串格式化
     * <br>
     *
     * @author wangyunshu
     */
    public static String strFormat(String _format, Object... args) {
        String format = _format.replace("{}", "%s");
        return String.format(Locale.ROOT, format, args);
    }

    /**
     * 按参数格式化字符串
     * <br>
     * 用map中的key-value替换字符串中的${key}
     * 如果map中没有对应的key则用指定的默认字符串替换
     * StringFormat test, ${index}: ${text:defaultValue}
     * ->
     * StringFormat test, indexValue: textValue
     *
     * @param _format 原始字符串格式
     * @param parameterMap 参数表
     * @author wangyunshu
     */
    public static String strFormat(String _format, Map<String, String> parameterMap) {
        StringSubstitutor sub = new StringSubstitutor(parameterMap);
        return sub.replace(_format);
    }

    /**
     * 拼接字符串
     * <br>
     *
     * @author wangyunshu
     */
    public static String join(String... strList) {
        StringBuilder builder = new StringBuilder();
        for (String str : strList) {
            if (isEmpty(str)) {
                continue;
            }
            builder.append(str);
        }
        return builder.toString();
    }

    public static String min(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return "";
        }
        return str1.compareTo(str2) > 0 ? str2 : str1;
    }

    public static String max(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return "";
        }
        return str1.compareTo(str2) > 0 ? str1 : str2;
    }

    public static String trim(String str) {
        return isEmpty(str) ? "" : str.trim();
    }
}
