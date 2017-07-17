package com.fight.dt.business.web.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tpx on 2017/7/15.
 */
public class ZkNodeListener implements NodeCacheListener, PathChildrenCacheListener {

    private static final Logger log = LoggerFactory.getLogger(ZkNodeListener.class);
    private NodeCache nodeCache;
    private PathChildrenCache pathChildrenCache;

    public ZkNodeListener(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    public ZkNodeListener(PathChildrenCache pathChildrenCache) {
        this.pathChildrenCache = pathChildrenCache;
    }

    public ZkNodeListener(NodeCache nodeCache, PathChildrenCache pathChildrenCache) {
        this.pathChildrenCache = pathChildrenCache;
        this.nodeCache = nodeCache;
    }

    @Override
    public void nodeChanged() {
        log.info("zkNodeChangedPath:" + nodeCache.getCurrentData().getPath());
        log.info("zkNodeChangedData:" + new String(nodeCache.getCurrentData().getData()));
        log.info("zkNodeChangedStat:" + nodeCache.getCurrentData().getStat());
    }

    @Override
    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        log.info("ZkChildEventType:" + event.getType().name());
        log.info("zkChildEventPath:" + event.getData().getPath());
        log.info("zkChildEventData:" + new String(event.getData().getData()));
        log.info("zkChildEventStat:" + event.getData().getStat());
    }

    public NodeCache getNodeCache() {
        return nodeCache;
    }

    public void setNodeCache(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    public PathChildrenCache getPathChildrenCache() {
        return pathChildrenCache;
    }

    public void setPathChildrenCache(PathChildrenCache pathChildrenCache) {
        this.pathChildrenCache = pathChildrenCache;
    }
}
