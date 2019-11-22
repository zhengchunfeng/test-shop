package com.test.task;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className JobAPI
 * @description 程序启动加载类
 * @date 2019/10/14 19:57
 */
@Component
public class JobAPI implements CommandLineRunner {

    /**
     * @description 应用启动后，加载一些配置信息
     * @author zhengchunfeng
     * @date 2019/10/15 11:33
     * @return void
     **/
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序启动成功...........");
        // 启动后连接zk实例
        CoordinatorRegistryCenter coordinatorRegistryCenter = JobUtils.coordinatorRegistryCenter();
        // 初始化作业，将作业注册到zk实例中
        initAllJobj(coordinatorRegistryCenter);
    }

    /**
     * @description 配置所有定时作业
     * @author zhengchunfeng
     * @date 2019/10/15 11:32
     * @param coordinatorRegistryCenter
     * @return void
     **/
    public void initAllJobj(CoordinatorRegistryCenter coordinatorRegistryCenter){
        // 取消预购资格作业
        new JobScheduler(coordinatorRegistryCenter, cancelPrewBuy()).init();
        // 用户积分过期
        new JobScheduler(coordinatorRegistryCenter, cancelUserScore()).init();
    }

   /**
    * @description 取消预购资格作业
    * @author zhengchunfeng
    * @date 2019/10/15 10:39 
    * @return com.dangdang.ddframe.job.lite.config.LiteJobConfiguration
    **/
    public static LiteJobConfiguration cancelPrewBuy() {

        JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder("cancelPrewBuy", "* * 1 * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(coreConfiguration, CancelPrewBuyTask.class.getCanonicalName());
        // 定义lite作业配置
        LiteJobConfiguration jobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
        return jobConfiguration;
    }

    /**
     * @description 用户积分过期作业
     * @author zhengchunfeng
     * @date 2019/10/15 10:39
     * @return com.dangdang.ddframe.job.lite.config.LiteJobConfiguration
     **/
    public static LiteJobConfiguration cancelUserScore() {

        JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder("cancelUserScore", "* * 1 * * ?", 1).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(coreConfiguration, CancelUserScoreTask.class.getCanonicalName());
        // 定义lite作业配置
        LiteJobConfiguration jobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
        return jobConfiguration;

    }



}
