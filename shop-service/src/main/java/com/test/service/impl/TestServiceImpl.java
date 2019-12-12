package com.test.service.impl;

import com.test.Thread.UserCountDownThread;
import com.test.Thread.UserCyclicBarrierThread;
import com.test.bean.constant.NumberConstant;
import com.test.bean.constant.RedisKeyConstant;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.concurrent.*;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestServiceImpl
 * @description 业务层
 * @date 2019/10/9 14:36
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {


    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @description 定义线程池(核心线程数10, 最大线程数20, KeepAliveTime60s)
     * 饱和策略：默认是直接abort
     * @author zhengchunfeng
     * @date 2019/11/29 19:21
     * @param null 1
     * @return
     **/
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(NumberConstant.NUMBER_TEN_INTEGER, NumberConstant.NUMBER_TWENTY_INTEGER, NumberConstant.NUMBER_SIXTY_INTEGER, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());


    /**
     * @description 测试方法
     * @author zhengchunfeng
     * @date 2019/11/25 10:37
     * @return java.lang.String
     **/
    @Override
    public String getApi(){

        // 1.发送一条消息
        kafkaTemplate.send("topic-20191212", "我是手动提交位移");

        // 2.累计加1
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String testKey = RedisKeyConstant.TEST_API_INCR + LocalDate.now().getYear();
        valueOperations.increment(testKey, NumberConstant.NUMBER_ONE_INTEGER);

        // 3.定义同步类
        CountDownLatch countDownLatch = new CountDownLatch(NumberConstant.NUMBER_TEN_INTEGER);
        for(int i = 0; i < 10; i ++){
            THREAD_POOL_EXECUTOR.execute(new UserCountDownThread(i, countDownLatch));
        }
        try {
            // 会阻塞主线程执行,直至子线程执行完毕
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "欢迎您访问";
    }

    /**
     * @description 用户签到
     * @author zhengchunfeng
     * @date 2019/12/4 19:12
     * @return void
     **/
    @Override
    public void userSignIn(){

        // 一定要注意，如果屏障数 > 核心线程数时，会导致子线程永远等待下去
        // 比如：本例核心线程数量10，屏障数100，那么就会只有10个子线程wait，其余都在阻塞队列里等待核心线程执行完毕才会从阻塞队列中执行
        // 因此屏障的数量一定要<= 核心线程数量保持一致
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
               log.info("所有用户已到达,优先执行BarrierAction");
            }
        });

        for (int i = 0; i< 100; i++){
            THREAD_POOL_EXECUTOR.submit(new UserCyclicBarrierThread(i, cyclicBarrier));
        }

    }

}
