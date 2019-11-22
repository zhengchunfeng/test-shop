package com.test.task;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className JobUtils
 * @description 作业工具类
 * @date 2019/10/14 19:59
 */
@Configuration
public class JobUtils {


    /**
     * @description 连接Zookeeper配置
     * @author zhengchunfeng
     * @date 2019/11/18 10:53
     * @param  1
     * @return com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter
     **/
    public static CoordinatorRegistryCenter coordinatorRegistryCenter(){

        // 此命名空间namespace一定要和作业控制监控台连接时保持一致
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter( new ZookeeperConfiguration("127.0.0.1:2181", "my_test_job"));
        registryCenter.init();
        return registryCenter;

    }


}
