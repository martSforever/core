package com.martsforever.core.img;

import com.martsforever.core.global.RequestManage;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("image")
public class ImageController {

    /**
     * 测试图片文件读写功能读取classes目录下的me.jpg文件，并创建一个新的文件me1.jpg，最后还把图片输出到response供用户下载或者查看
     *
     * @param req
     * @param resp
     * @return
     */
    @GetMapping("readWriteImg")
    public Map<String, Object> getImg(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<>();
        try {
            String resourceName = "me.jpg";
            /*获取classes目录绝对路径*/
            String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
            System.out.println(path);
            /*读取文件*/
            FileInputStream fs = new FileInputStream(path);
            BufferedImage bi = ImageIO.read(fs);
            File file = new File("/D:/6_workspace/ideaspace_3/core/target/classes/me1.jpg");
            /*写入文件*/
            ImageIO.write(bi, "jpg", file);
            BufferedInputStream bis = new BufferedInputStream(fs);
            byte[] buffer = new byte[bis.available()];
            bis.read(buffer);
            /*输出文件响应请求*/
            resp.getOutputStream().write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", true);
        return result;
    }

    /**
     * 测试图片上传，跟一般的图片上传不一样的是，这里只接受dataUrl，然后将dataUrl解析为二进制流，最后保存为图片文件
     *
     * @param ii
     * @return
     */
    @PostMapping("uploadImg")
    public Map<String, Object> uploadImg(@RequestBody ImageInfo ii) {
        System.out.println(ii.getDataUrl());
        System.out.println(ii.getName());
        try {
            ImgUtils.saveDataUrl(ii);
            return RequestManage.success(ii);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestManage.error(null, "系统异常！");
    }

}
