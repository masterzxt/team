package com.fight.dt.business.service;

import com.fight.dt.business.common.beans.Item;
import com.fight.dt.business.dao.ItemDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tpx on 2017/7/28.
 */
public interface ItemService {

    Item findById(Integer id);

    Item findByItem(Item item);

    int insert(Item item);

    int update(Item item);

    void info(Item item);

    List<Item> findAll(String itemId, String sellerId, Integer taskStatus, Integer page, Integer pageSize);
}
