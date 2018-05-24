package com.martsforever.core.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskBean {

    /**
     * 每分钟启动
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow() {
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
