package com.test.Thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserThread
 * @description 多线程-CountDownLatch-demo
 * @date 2019/11/29 19:17
 */
public class UserCountDownThread implements Runnable{

    private Integer index;

    private CountDownLatch countDownLatch;

    public UserCountDownThread(Integer index, CountDownLatch countDownLatch) {
        this.index = index;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + index);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 最后一定要countDown() 做递减操作，否则影响主线程执行
            countDownLatch.countDown();

        }

    }
}
