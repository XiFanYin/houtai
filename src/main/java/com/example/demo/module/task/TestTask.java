package com.example.demo.module.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务，他会在启动时候自动开启定时任务
 */
@Component
public class TestTask {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss ");
    //http://cron.qqe2.com/
    @Scheduled(cron="4-40 * * * * ? ")
    public void  reportCurrentTime(){
        System.out.println("当前时间是"+dataFormat.format(new Date()));

     }

}
