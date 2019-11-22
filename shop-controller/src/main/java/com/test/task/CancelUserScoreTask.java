package com.test.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className CancelUserScoreTask
 * @description 用户积分过期作业
 * @date 2019/11/18 11:42
 */
@Slf4j
@Component
public class CancelUserScoreTask implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        log.info("用户积分过期作业....");
    }
}
