package com.martsforever.core.template.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("xcx/attachment")
public class UploadController {

    @RequestMapping("uploadImage")
    @ResponseBody
    public Map<String, Object> fileUpload(@RequestParam("image") MultipartFile file) {

        Map<String, Object> result = new HashMap<>();

        if (file.isEmpty()) {
            result.put("code", 0);
        }
        String fileName = file.getOriginalFilename();
        String time = new Date().getTime() + "";
        String name = time + fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        System.out.println(fileName + "-->" + name);
        String path = "D:/filedata";
        File dest = new File(path + "/" + name);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            result.put("code", 0);
            result.put("name", name);
        } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.put("code", 0);
        }
        return result;
    }

}
