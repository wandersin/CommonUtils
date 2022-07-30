package vip.mrtree.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtils {
    private static final int DEFAULT_FILE_READ_LENGTH = 1024;

    /**
     * 读取文件
     * <br>
     *
     * @param path 文件路径
     * @author wangyunshu
     */
    public static String readFile(String path) throws IOException {
        if (StringUtils.isEmpty(path)){
            throw new IOException("Path error.");
        }
        return readFile(new File(path));
    }

    /**
     * 读取文件
     * <br>
     *
     * @param file 文件对象
     * @author wangyunshu
     */
    public static String readFile(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IOException("File not find.");
        }
        try(InputStream in = Files.newInputStream(file.toPath())) {
            return readFile(in);
        }
    }

    public static String readFile(InputStream inputStream) throws IOException {
        byte[] cache = new byte[DEFAULT_FILE_READ_LENGTH];
        StringBuilder builder = new StringBuilder();
        int temp;
        while ((temp = inputStream.read(cache)) != -1) {
            builder.append(new String(cache, 0, temp));
        }
        return builder.toString();
    }

    /**
     * 获取文件扩展名
     * <br>
     *
     * @return 扩展名
     * @author wangyunshu
     */
    public static String getFileExtension(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return index < 0 ? "" : name.substring(index + 1);
    }
}
