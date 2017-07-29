package com.fight.dt.business.web.task;

import com.fight.dt.business.common.beans.Item;
import com.fight.dt.business.common.core.ItemTaskStatusEnum;
import com.fight.dt.business.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tpx on 2017/7/29.
 */
@Service("itemInfoTask")
public class ItemInfoTask implements InitializingBean, Runnable {

    @Resource
    private ItemService itemService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //@Scheduled(cron = "0/10 0 0 ? ? ?")
    public void run() {
        while (true) {
            List<Item> itemList = itemService.findAll(null, null, ItemTaskStatusEnum.WAIT.getCode(), 0, 1);
            if (itemList != null && !itemList.isEmpty()) {
                for (int i = 0; i < itemList.size(); i++) {
                    Item item = itemList.get(i);
                    item.setTaskStatus(ItemTaskStatusEnum.START.getCode());
                    if (itemService.update(item) <= 0) {
                        logger.error(new StringBuffer("ItemInfoTask#info#update error id=").append(item.getId()).toString());
                        return;
                    }
                    try {
                        itemService.info(item);
                        Thread.sleep(30 * 1000);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }

                }
            } else {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException ex) {
                    logger.error(ex.getMessage(), ex);
                }

            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(this).start();
    }
}
