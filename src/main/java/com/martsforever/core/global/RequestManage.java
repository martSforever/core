package com.martsforever.core.global;

import java.util.HashMap;
import java.util.Map;

public class RequestManage {

    private RequestManage() {
    }

    public static Map<String, Object> success(Object object) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.SUCCESS, true);
        result.put(Constants.RESULT, object);
        return result;
    }

    public static Map<String, Object> error(Object object, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.SUCCESS, false);
        result.put(Constants.RESULT, object);
        result.put(Constants.MESSAGE, msg);
        return result;
    }

}
