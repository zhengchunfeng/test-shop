package com.test.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description 取消预购资格作业
 * @author zhengchunfeng
 * @date 2019/11/18 13:31
 * @param null 1
 * @return
 **/
@Component
@Slf4j
public class CancelPrewBuyTask implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {

        log.info("我是取消预购资格作业定时任务...");
        // 获取分片总数
        int shardingTotalCount = shardingContext.getShardingTotalCount();

        // 获取分片项
        int shardingItem = shardingContext.getShardingItem();

        // 获取分片项参数
        String shardingParameter = shardingContext.getShardingParameter();

        log.info("分片总数="+shardingTotalCount);
        log.info("分片项="+shardingItem);
        log.info("分片项参数="+shardingParameter);
        log.info("作业名称="+shardingContext.getJobName());

        // 不同分片项不同处理
        switch (shardingItem){
            case 0:
                log.info("第1个"+shardingParameter);
                break;
            case 1:
                log.info("第2个"+shardingParameter);
                break;
            case 2:
                log.info("第3个"+shardingParameter);
                break;
            default:break;
        }

        // 具体定时任务执行的事项处理....


    }
}
