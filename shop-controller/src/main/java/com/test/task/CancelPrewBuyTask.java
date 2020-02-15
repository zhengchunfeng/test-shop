package com.test.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description 取消预购资格作业
 * @author zhengchunfeng
 * @date 2019/11/18 13:31
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

       // log.info("分片总数="+shardingTotalCount);
       // log.info("分片项="+shardingItem);
       // log.info("分片项参数="+shardingParameter);
       // log.info("作业名称="+shardingContext.getJobName());

        // 不同分片项不同处理
        switch (shardingItem){
            case 0:
                // 参数为mobile，专门处理mobile渠道的数据
                log.info("分片0："+shardingParameter);
                break;
            case 1:
                // 参数为pc，专门处理pc渠道的数据
                log.info("分片1："+shardingParameter);
                break;
            case 2:
                // 参数为unkown， 专门处理unkown渠道的数据
                log.info("分片2："+shardingParameter);
                break;
            default:break;
        }

        // 具体定时任务执行的事项处理....


    }
}
