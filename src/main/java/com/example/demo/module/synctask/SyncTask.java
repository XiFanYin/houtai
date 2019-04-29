package com.example.demo.module.synctask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步调用方法
 */
@Component
public class SyncTask {

    @Async
    public Future<Boolean> doTask11() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时："+(end-start)+"毫秒");
        return  new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> doTask22() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(700);
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时："+(end-start)+"毫秒");
        return  new AsyncResult<>(true);
    }
    @Async
    public Future<Boolean> doTask33() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时："+(end-start)+"毫秒");
        return  new AsyncResult<>(true);
    }


}
