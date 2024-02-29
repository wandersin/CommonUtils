package vip.mrtree.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Collection;
import java.util.List;

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

    /**
     * 去除windows中文件名不允许的字符
     * <br>
     *
     * @author wangyunshu
     */
    public static String getWindowsCleanFileName(String name) {
        return name.replace("\\", "")
            .replace("/", "")
            .replace(":", "")
            .replace("*", "")
            .replace("?", "")
            .replace("\"", "")
            .replace("<", "")
            .replace(">", "")
            .replace("|", "");
    }

    /**
     * 判断路径是否在白名单内
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean include(Path path, Collection<String> include) {
        List<PathMatcher> includeMatcher = include.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        if (CollectionUtils.isEmpty(includeMatcher)) {
            return Boolean.TRUE;
        }
        return includeMatcher.stream().anyMatch(matcher -> matcher.matches(path));
    }

    /**
     * 判断路径是否在黑名单内
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean exclude(Path path, Collection<String> exclude) {
        List<PathMatcher> excludeMatcher = exclude.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        if (CollectionUtils.isEmpty(excludeMatcher)) {
            return Boolean.FALSE;
        }
        return excludeMatcher.stream().anyMatch(matcher -> matcher.matches(path));
    }

    /**
     * 判断文件是否符合黑白名单要求
     * <br>
     *
     * @author wangyunshu
     */
    public static boolean filter(Path path, Collection<String> include, Collection<String> exclude) {
        return include(path, include) && !exclude(path, exclude);
    }
}
