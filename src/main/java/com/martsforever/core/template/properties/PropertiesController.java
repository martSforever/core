package com.martsforever.core.template.properties;

import com.martsforever.core.global.RequestManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PropertiesController {

    /*获取yml配置文件中单个配置*/
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    /*获取配置文件中配置对象*/
    @Autowired
    private PropertiesObj propertiesObj;

    @RequestMapping("getVariable")
    public Map<String, Object> getVariable() {
        return RequestManage.success(databaseUrl);
    }

    @RequestMapping("getPropertiesObj")
    public Map<String, Object> getPropertiesObj() {
        return RequestManage.success(propertiesObj);
    }

}
