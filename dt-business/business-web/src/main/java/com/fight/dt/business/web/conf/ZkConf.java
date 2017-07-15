package com.fight.dt.business.web.conf;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/15.
 */
@Configuration
public class ZkConf {

    @Resource
    private Environment environment;
    @Resource
    RetryPolicy retryPolicy;

    @Bean
    RetryPolicy retryPolicy(){
        return new ExponentialBackoffRetry(
                    Integer.parseInt(environment.getProperty("zookeeper.retryPolicy.baseSleepTimeMs")),
                    Integer.parseInt(environment.getProperty("zookeeper.retryPolicy.maxRetries")),
                    Integer.parseInt(environment.getProperty("zookeeper.retryPolicy.maxSleepMs"))
                );
    }

    @Bean
    CuratorFramework curatorFramework(){
      return  CuratorFrameworkFactory.builder()
                .connectString(environment.getProperty("zookeeper.connectString"))
                .sessionTimeoutMs(Integer.parseInt(environment.getProperty("zookeeper.sessionTimeoutMs")))
                .connectionTimeoutMs(Integer.parseInt(environment.getProperty("zookeeper.connectionTimeoutMs")))
                .retryPolicy(retryPolicy)
                .build();
    }

}
