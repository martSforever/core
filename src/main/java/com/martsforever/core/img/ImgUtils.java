package com.martsforever.core.img;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImgUtils {

    /*用来将二进制字符串解析成byte数组*/
    private static BASE64Decoder base64Decoder = new BASE64Decoder();
    /*项目根目录*/
    private static String rootPath = new File("").getAbsolutePath() + "\\";

    private static final int MID_WIDTH = 300;
    private static final int SMALL_WIDTH = 50;

    /**
     * 传入一个ImageInfo对象；
     * ii.dataUrl：为用来解析为二进制流的字符串，在浏览器端通过FileReader的getAsDataUrl方法获得；
     * ii.name：最后文件保存的名称
     * ii.path：文件保存的路径，咩有则使用项目根目录
     *
     * @param ii
     * @return ii，最后更新的信息为图片文件的大小以及图片保存的路径
     * @throws IOException
     */
    public static ImageInfo saveDataUrl(ImageInfo ii) throws Exception {
        ii.setDataUrl(ii.getDataUrl().substring(ii.getDataUrl().lastIndexOf(",") + 1));
        if (ii.getPath() == null || ii.getPath().equals("")) {
            ii.setPath(rootPath);
        }
        byte[] bytes = base64Decoder.decodeBuffer(ii.getDataUrl());
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(bais);
        ImageInfo sbi = saveBigImage(bi, rootPath, ii.getName());
        ImageInfo smi = saveMidImage(bi, rootPath, ii.getName());
        ImageInfo ssi = saveSmallImage(bi, rootPath, ii.getName());
        ii.setPath(sbi.getPath());
        ii.setSize(sbi.getSize());
        ii.setMidPath(smi.getPath());
        ii.setMidSize(smi.getSize());
        ii.setSmallPath(ssi.getPath());
        ii.setSmallSize(ssi.getSize());
        ii.setType(ii.getName().substring(ii.getName().lastIndexOf(".") + 1));
        return ii;
    }

    /**
     * 缩放图片
     *
     * @param oldBi     图片源
     * @param fixWidth  固定宽度缩小
     * @param fixHeight 固定高度缩小
     * @return
     */
    private static BufferedImage scaleImage(BufferedImage oldBi, Integer fixWidth, Integer fixHeight) throws Exception {
        int width = oldBi.getWidth();
        int height = oldBi.getHeight();
        /*如果输入的固定宽度为空，则按照固定高度缩小，反之亦然*/
        if (fixWidth == null) {
            fixWidth = (width * fixHeight / height);
        } else if (fixHeight == null) {
            fixHeight = (height * fixWidth / width);
        } else {
            throw new Exception("固定宽度或者固定宽度不能同时为空！");
        }
        BufferedImage newBi = new BufferedImage(fixWidth, fixHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = newBi.getGraphics();
        g.drawImage(oldBi.getScaledInstance(fixWidth, fixHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();
        return newBi;
    }

    /**
     * 保存图片为文件
     *
     * @param bi
     * @param fullPath
     * @return
     * @throws IOException
     */
    private static ImageInfo saveImage(BufferedImage bi, String fullPath) throws IOException {
        ImageInfo imageInfo = new ImageInfo();

        File newFile = new File(fullPath);
        if (newFile.exists()) {
            System.out.println("文件已经存在：" + fullPath);
        } else {
            ImageIO.write(bi, "png", newFile);
            imageInfo.setPath(fullPath);
            imageInfo.setSize(newFile.length() + "");
        }
        return imageInfo;
    }

    /**
     * 保存大图片，就是原图
     *
     * @param bi
     * @param path
     * @param fileName
     * @return
     * @throws Exception
     */
    private static ImageInfo saveBigImage(BufferedImage bi, String path, String fileName) throws Exception {
        return saveImage(bi, path + "big." + fileName);
    }

    /**
     * 保存中图片
     *
     * @param bi
     * @param path
     * @param fileName
     * @return
     * @throws Exception
     */
    private static ImageInfo saveMidImage(BufferedImage bi, String path, String fileName) throws Exception {
        bi = scaleImage(bi, null, MID_WIDTH);
        return saveImage(bi, path + "mid." + fileName);
    }

    /**
     * 保存小图片
     *
     * @param bi
     * @param path
     * @param fileName
     * @return
     * @throws Exception
     */
    private static ImageInfo saveSmallImage(BufferedImage bi, String path, String fileName) throws Exception {
        bi = scaleImage(bi, null, SMALL_WIDTH);
        return saveImage(bi, path + "small." + fileName);
    }

}
