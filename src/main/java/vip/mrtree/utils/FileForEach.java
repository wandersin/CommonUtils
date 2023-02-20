package vip.mrtree.utils;

import java.io.File;

public interface FileForEach {
    /**
     * 遍历文件夹下所有文件, 遍历后不删除文件
     * <br>
     * @param dir 根路径
     * @author wangyunshu
     */
    default void dirForEach(File dir) {
        dirForEach(dir, Boolean.FALSE);
    }

    /**
     * 遍历文件夹下所有文件, 可配置遍历后是否删除文件及文件夹
     * <br>
     * @param dir 根路径
     * @param delete 遍历后是否删除
     * @author wangyunshu
     */
    default void dirForEach(File dir, boolean delete) {
        if (!dir.exists()) {
            // dir不存在
            return;
        }
        File[] children = dir.listFiles();
        if (children == null) {
            // 没有子文件
            return;
        }
        for (File child : children) {
            if (child.isDirectory()) {
                // 遍历子文件夹
                dirForEach(child, delete);
            } else {
                // 执行操作
                execute(child);
            }
        }
        if (delete) {
            // 删除文件夹
            boolean result = dir.delete();
            if (!result) {
                throw new RuntimeException(StringUtils.strFormat("删除文件失败: {}", dir.getAbsolutePath()));
            }
        }
    }

    /**
     * 需事先该方法, 用于对根路径下每个文件进行固定操作
     * <br>
     *
     * @author wangyunshu
     */
    void execute(File child);
}
