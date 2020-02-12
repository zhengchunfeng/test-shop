package com.test.Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserSemaphoreThread
 * @description 信号量
 * @date 2020/1/14 16:43
 */
@Slf4j
public class UserSemaphoreThread implements Callable<Map<String, Object>> {

    private Semaphore semaphore;

    private int index;

    public UserSemaphoreThread(Semaphore semaphore, int index) {
        this.semaphore = semaphore;
        this.index = index;
    }

   /**
    * @description 重写call方法
    * @author zhengchunfeng
    * @date 2020/2/12 21:31
    * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    @Override
    public Map<String, Object> call(){

        try {
            log.info("变量[{}]准备获取锁acquire", index);
            // 获取锁
            semaphore.acquire();
            log.info("变量[{}]已成功获取锁", index);
            // 执行具体业务
            Thread.sleep(8000);
            log.info("-----------------------------");

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            semaphore.release();
        }
        log.info("变量[{}]释放锁", index);

        return new HashMap<>();

    }
}
