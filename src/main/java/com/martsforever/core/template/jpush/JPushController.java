package com.martsforever.core.template.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.martsforever.core.global.RequestManage;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

import static javax.accessibility.AccessibleRole.ALERT;

@RestController
@RequestMapping("push")
public class JPushController {
    private static String MASTER_SECRET = "5a1c4d4abb80ac481a44257a";
    private static String APP_KEY = "41259c975595d3c56c9e74ef";

    @PostMapping("sendAll")
    public static Map<String, Object> sendAll(@RequestBody PushMessage pushMessage) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert(pushMessage.getMessage());
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            System.out.println("Connection error, should retry later" + e.getMessage());

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            System.out.println("Should review the error, and fix the request" + e.getErrorMessage());
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
        return RequestManage.success(pushMessage);
    }

    public static PushPayload buildPushObject_all_all_alert(String msg) {

        return PushPayload.alertAll(msg);
    }

    //发给一个客户端
    public static PushPayload buildPushObject_all_registrationid_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all()) //设置平台-所有平台
                .setAudience(Audience.registrationId("")) //设置受众-极光注册id
                .setNotification(Notification.alert(ALERT)) //设置通知 - 消息
                .build();
    }

    //多个客户端
    public static PushPayload buildPushObject_all_registrationids_alert(Collection<String> strings) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all()) //设置平台-所有平台
                .setAudience(Audience.registrationId(strings)) //设置受众-极光注册id-多个客户端
                .setNotification(Notification.alert(ALERT)) //设置通知-推送信息
                .build();
    }
}
