package com.fight.dt.business.web;

import com.fight.dt.business.web.listener.ZkNodeListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tpx on 2017/7/10.
 */
@Profile("dev")
@SpringBootApplication(scanBasePackages="com.fight.dt.business")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        //测试redis发布与订阅
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        template.convertAndSend("chat", "Hello from Redis!");

        latch.await();

        //测试zookeeper
        String path = "/zkClient/nodeCache";
        //获取链接
        CuratorFramework zkClient = (CuratorFramework)ctx.getBean(CuratorFramework.class);
        try{
            zkClient.delete().forPath(path);
        }catch(Exception e){

        }

        //创建节点并且初始化节点内容数据
        zkClient.create()
                .creatingParentContainersIfNeeded()
        /**
         *   PERSISTENT(0, false, false, false), //持久结点
         *   PERSISTENT_SEQUENTIAL(2, false, true, false),  //持久顺序结点
         *   EPHEMERAL(1, true, false, false), //临时结点  session会话结束消失
         *   EPHEMERAL_SEQUENTIAL(3, true, true, false), // 临时顺序
         *   CONTAINER(4, false, false, true); //容器结点 本地的? TODO
         */
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
        NodeCache nodeCache = new NodeCache(zkClient, path ,false);
        //true启动时获取数据内容 默认false
        nodeCache.start(true);
        ZkNodeListener zkNodeListener = new ZkNodeListener(nodeCache);
        nodeCache.getListenable().addListener(zkNodeListener);
        //改变信息
        zkClient.setData().forPath(path,"test".getBytes());


    }
}
