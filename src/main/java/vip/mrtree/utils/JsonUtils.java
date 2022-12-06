package vip.mrtree.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {
    /**
     * 对象转为json字符串
     * <br>
     *
     * @author wangyunshu
     */
    public static String toJsonString(Object obj, SerializerFeature... features) {
        String result;
        try {
            result = JSONObject.toJSONString(obj, features);
        } catch (Exception e) {
            result = "解析对象格式失败";
        }
        return result;
    }

    public static boolean isEmpty(JSONArray array) {
        return array == null || array.size() == 0;
    }
}
