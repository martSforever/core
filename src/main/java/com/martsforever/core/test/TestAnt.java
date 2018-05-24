package com.martsforever.core.test;


import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestAnt {

    public static final String STORE_SAMPLE_IMG_ROOT_PATH = "/filedata/DMS/app/storeSample/";
    public static final String STORE_SAMPLE_IMG_DIR_NAME = "hgStoreSampleImages";
    public static final String PREFIX_PATH = "/app/storeSample/" + STORE_SAMPLE_IMG_DIR_NAME + "/";

    @Test
    public void test1() {
        String zipFileName = STORE_SAMPLE_IMG_DIR_NAME + ".zip";
        String path = STORE_SAMPLE_IMG_ROOT_PATH;
        String sourcePath = path + zipFileName;

        /*在解压之前，把目录下除了hgStoreSampleImages.zip以外的其他文件或者文件夹删除*/
        FileUtils.deleteOtherFileOrDir(path, zipFileName);
        /*解压文件*/
        ZipUtils.unzip(sourcePath, path);
        /*重命名解压之后的文件夹*/
        File rootDir = new File(path);
        if (!rootDir.isDirectory()) {
            throw new RuntimeException("地址【" + path + "】不为文件夹，请检查！");
        }
        String[] files = rootDir.list();
        for (String fileName : files) {
            String filePath = rootDir.getAbsolutePath() + "\\" + fileName;
            if (fileName != null && !fileName.equals(zipFileName)) {
                File targetDir = new File(filePath);
                targetDir.renameTo(new File(rootDir.getAbsoluteFile() + "\\" + STORE_SAMPLE_IMG_DIR_NAME));
            }
        }
        /*扫描图片*/
        List<HgStoreImage> images = scanImages();
        for (HgStoreImage image : images) {
            System.out.println(image);
        }
    }

    /**
     * 扫描门店平面布局图，打印访问地址
     */
    public List<HgStoreImage> scanImages() {
        List<HgStoreImage> images = new ArrayList<>();
        File hgStoreSampleImagesDir = new File(STORE_SAMPLE_IMG_ROOT_PATH + STORE_SAMPLE_IMG_DIR_NAME);
        String rootDirPath = hgStoreSampleImagesDir.getAbsolutePath();
        String[] storeCodes = hgStoreSampleImagesDir.list();
        for (String storeCode : storeCodes) {
            File storeDir = new File(rootDirPath + "/" + storeCode);
            String[] imageNames = storeDir.list();
            for (String imageName : imageNames) {
                images.add(new HgStoreImage(storeCode, PREFIX_PATH + storeCode + "/" + imageName));
            }
        }
        return images;
    }


}
