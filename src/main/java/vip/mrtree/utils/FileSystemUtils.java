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
     * @param walkDir 是否将文件夹加入扫描结果
     * @param walkFile 是否将文件加入扫描结果
     * @author wangyunshu
     */
    public static Collection<Path> walkFileTree(String root, Collection<String> include, Collection<String> exclude, boolean walkDir, boolean walkFile) {
        Set<Path> result = new HashSet<>();
        Path path = Paths.get(root);
        if (!path.toFile().exists()) {
            return result;
        }
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!walkDir) {
                        return FileVisitResult.CONTINUE;
                    }
                    if (FileSystemUtils.isFileEffective(dir, include, exclude)) {
                        result.add(dir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (!walkFile) {
                        return FileVisitResult.CONTINUE;
                    }
                    if (FileSystemUtils.isFileEffective(file, include, exclude)) {
                        result.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ignored) {

        }
        return result;
    }

    /**
     * 判断文件是否满足include规则并且不满足exclude规则
     * <br>
     *
     * @author wangyunshu
     */
    private static boolean isFileEffective(Path path, Collection<String> include, Collection<String> exclude) {
        List<PathMatcher> includeMatcher = include.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        List<PathMatcher> excludeMatcher = exclude.stream()
            .filter(StringUtils::isNotEmpty)
            .map(pattern -> FileSystems.getDefault().getPathMatcher(pattern))
            .toList();
        boolean matchesInclude;
        if (CollectionUtils.isEmpty(include)) {
            matchesInclude = Boolean.TRUE;
        } else {
            matchesInclude = includeMatcher.stream().anyMatch(matcher -> matcher.matches(path));
        }
        boolean matchesExclude;
        if (CollectionUtils.isEmpty(exclude)) {
            matchesExclude = Boolean.FALSE;
        } else {
            matchesExclude = excludeMatcher.stream().anyMatch(matcher -> matcher.matches(path));
        }
        return matchesInclude && !matchesExclude;
    }

    public static Collection<Path> walkFileTree(String root, String include, String exclude) {
        return walkFileTree(root , Collections.singleton(include), Collections.singleton(exclude), false, true);
    }

    public static Collection<Path> walkFileTree(String root) {
        return walkFileTree(root, Collections.emptySet(), Collections.emptySet(), false, true);
    }

    public static Collection<Path> walkFileTree(String root, Collection<String> include, Collection<String> exclude) {
        return walkFileTree(root, include, exclude, false, true);
    }

    public static Collection<Path> walkFileTree(Collection<String> root, Collection<String> include, Collection<String> exclude) {
        Set<Path> result = new HashSet<>();
        root.forEach(path -> result.addAll(walkFileTree(path, include, exclude, false, true)));
        return result;
    }

    public static Collection<Path> walkFileTree(Collection<String> root, String include, String exclude) {
        return walkFileTree(root, Collections.singleton(include), Collections.singleton(exclude));
    }

    public static Collection<Path> walkFileTree(Collection<String> root) {
        return walkFileTree(root, Collections.emptySet(), Collections.emptySet());
    }
}
