package com.martsforever.core.home;

import com.martsforever.core.global.RequestManage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("getMenus")
    public Map<String, Object> getMenus() {
        List<String> menus = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            menus.add((i + 1) + "");
//        return RequestManage.error(menus,"性别不正确！");
        return RequestManage.success(menus);
    }

    @GetMapping("errorRequest")
    public Map<String, Object> errorRequest() {
        List<String> menus = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            menus.add((i + 1) + "");
//        return RequestManage.error(menus,"性别不正确！");
        return RequestManage.error(menus,"服务器异常！");
    }
}
