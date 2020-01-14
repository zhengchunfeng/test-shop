package com.test.Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserCyclicBarrierThread
 * @description CyclicBarrier线程使用
 * @date 2019/12/4 19:13
 */
@Slf4j
public class UserCyclicBarrierThread implements Callable<Map<String, Object>> {

    private Integer index;

    private CyclicBarrier cyclicBarrier;

    public UserCyclicBarrierThread(Integer index, CyclicBarrier cyclicBarrier) {
        this.index = index;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public Map<String, Object> call(){


        log.info("====线程序号[{}]已到达，准备开始签到...", index);
        try {
            // 阻塞子线程
            cyclicBarrier.await();
            log.info("====线程序号[{}]签到成功", index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<>();

    }

    public static void main(String[] args) {
        Thread thread = new Thread();
    }

}
