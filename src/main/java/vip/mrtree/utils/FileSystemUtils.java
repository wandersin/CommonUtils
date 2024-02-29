package vip.mrtree.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileSystemUtils {
    /**
     * 遍历文件系统
     * <br>
     * include不为空时, 白名单生效
     * exclude不为空时, 黑名单生效
     *
     * @param root 需要遍历的根路径
     * @param include 路径白名单的glob表达式
     * @param exclude 路径黑名单的glob表达式
     * @author wangyunshu
     */
    public static Collection<Path> walkFileTree(String root, Collection<String> include, Collection<String> exclude) {
        Set<Path> result = new HashSet<>();
        Path path = Paths.get(root);
        if (!path.toFile().exists()) {
            return result;
        }
        List<PathMatcher> includeMatcher = include.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        List<PathMatcher> excludeMatcher = exclude.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    boolean matchesInclude;
                    if (CollectionUtils.isEmpty(include)) {
                        matchesInclude = Boolean.TRUE;
                    } else {
                        matchesInclude = includeMatcher.stream().anyMatch(matcher -> matcher.matches(file));
                    }
                    boolean matchesExclude;
                    if (CollectionUtils.isEmpty(exclude)) {
                        matchesExclude = Boolean.FALSE;
                    } else {
                        matchesExclude = excludeMatcher.stream().anyMatch(matcher -> matcher.matches(file));
                    }
                    if (matchesInclude && !matchesExclude) { // 匹配任一白名单规则且不匹配所有黑名单规则
                        result.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ignored) {

        }
        return result;
    }

    public static Collection<Path> walkFileTree(String root, String include, String exclude) {
        return walkFileTree(root , Collections.singleton(include), Collections.singleton(exclude));
    }

    public static Collection<Path> walkFileTree(String root) {
        return walkFileTree(root, Collections.emptySet(), Collections.emptySet());
    }

    public static Collection<Path> walkFileTree(Collection<String> root, Collection<String> include, Collection<String> exclude) {
        Set<Path> result = new HashSet<>();
        root.forEach(path -> result.addAll(walkFileTree(path, include, exclude)));
        return result;
    }

    public static Collection<Path> walkFileTree(Collection<String> root, String include, String exclude) {
        return walkFileTree(root, Collections.singleton(include), Collections.singleton(exclude));
    }

    public static Collection<Path> walkFileTree(Collection<String> root) {
        return walkFileTree(root, Collections.emptySet(), Collections.emptySet());
    }
}
