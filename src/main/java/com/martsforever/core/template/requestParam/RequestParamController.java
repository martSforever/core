package com.martsforever.core.template.requestParam;

import com.martsforever.core.global.RequestManage;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RequestParamController {

    /**
     * 从路径中获取参数
     * 好像是，如果路径中没有/{id}这个参数的话，会显示404错误，找不到该路径
     * 正确的：localhost:9321/hello/testPathVariableAnnotation/24
     * 错误的：localhost:9321/hello/testPathVariableAnnotation（会报404错误），也就是说，参数不能为空
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "testPathVariableAnnotation/{id}")
    public Map<String, Object> testPathVariableAnnotation(@PathVariable("id") Integer id) {
        return RequestManage.success("id:" + id);
    }

    /**
     * 从requestParam中获取参数
     * localhost:9321/hello/testRequestParam?id=22222
     * localhost:9321/hello/testRequestParam?id=    （id默认为-1）
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer id) {
        return "id:" + id;
    }


    /**
     * 从requestBody中获取参数
     *
     * @param target
     * @return
     */
    @RequestMapping("testRequestBody")
    public Map<String, Object> testRequestBody(@RequestBody ParamTarget target) {
        System.out.println(target.toString());
        return RequestManage.success(target);
    }


}
