package vip.mrtree.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
    /**
     * 对象转为json字符串
     * <br>
     *
     * @author wangyunshu
     */
    public static String toJsonString(Object obj) {
        String result;
        try {
            result = JSONObject.toJSONString(obj);
        } catch (Exception e) {
            result = "解析对象格式失败";
        }
        return result;
    }

    public static boolean isEmpty(JSONArray array) {
        return array == null || array.size() == 0;
    }
}
