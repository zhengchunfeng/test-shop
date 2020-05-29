package com.test.Thread;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhengchunfeng
 * @version 1.0
 * @className DeadLock
 * @description 死锁案例demo
 * @date 2020/5/29 11:22
 */
@Slf4j
public class DeadLock{

    public static void main(String[] args) {
        WorkDo a = new WorkDo(1);
        WorkDo b = new WorkDo(2);
        a.start();
        b.start();
    }

    static class WorkDo extends Thread {
        private int code;
        public WorkDo(int code) {
            this.code = code;
        }
        @Override
        public void run() {
           if(code == 1){
               try {
                   methodA();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }else {
               try {
                   methodB();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }

        public void methodA() throws InterruptedException {
            synchronized(String.class){
                log.info("methodA：获取String类锁");
                synchronized (Integer.class){
                    log.info("methodB：获取Integer类锁");
                }
                log.info("------methodA：成功-------------");
            }
        }

        public void methodB() throws InterruptedException {
            synchronized(Integer.class){
                log.info("methodB：获取Integer类锁");
                synchronized (String.class){
                    log.info("methodB：获取String类锁");
                }
                log.info("------methodB：成功------------");
            }

        }

    }




}
