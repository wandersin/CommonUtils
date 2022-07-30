package vip.mrtree.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DigestUtils {
    /**
     * 字符串base64转码
     * <br>
     *
     * @author wangyunshu
     */
    public static String base64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 计算文件md5
     * <br>
     *
     * @author wangyunshu
     */
    public static String md5(File file) {
        try(FileInputStream in = new FileInputStream(file)) {
            return org.apache.commons.codec.digest.DigestUtils.md5Hex(in);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * md5加密 - 不加盐
     * <br>
     *
     * @author wangyunshu
     */
    public static String md5(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(str);
    }

    /**
     * md5加密 - 加盐
     * <br>
     * 使用当前时间戳作为盐值
     * @param str 待加密字符串
     * @return md5
     * @author wangyunshu
     */
    public static String md5WithSalt(String str) {
        return md5WithSalt(str, DateUtils.getTimestamp());
    }

    /**
     * md5加密 - 加盐
     * <br>
     *
     * @param str 待加密字符串
     * @param salt 盐值
     * @return md5
     * @author wangyunshu
     */
    public static String md5WithSalt(String str, String salt) {
        return md5(StringUtils.join(md5(str), salt));
    }

    /**
     * 计算sha1
     * <br>
     *
     * @author wangyunshu
     */
    public static String sha1(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(str);
    }

    /**
     * 计算sha256
     * <br>
     *
     * @author wangyunshu
     */
    public static String sha256(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
    }
}
