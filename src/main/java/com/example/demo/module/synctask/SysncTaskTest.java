package com.example.demo.module.synctask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/sysnctask")
public class SysncTaskTest {

 //   任务三耗时：600毫秒
//    任务二耗时：700毫秒
//    任务一耗时：1000毫秒
//    任务一共同耗时：1010毫秒
    @Autowired
    SyncTask syncTask;


    @RequestMapping("/sysnctask")
    public void TestSysnc() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<Boolean> a = syncTask.doTask11();
        Future<Boolean> b = syncTask.doTask22();
        Future<Boolean> c = syncTask.doTask33();

        while (!a.isDone() || !b.isDone() || !c.isDone()) {
            if (a.isDone() && b.isDone() && c.isDone())
                break;
        }
        long end = System.currentTimeMillis();

        System.out.println("任务一共同耗时：" + (end - start) + "毫秒");

    }

}
