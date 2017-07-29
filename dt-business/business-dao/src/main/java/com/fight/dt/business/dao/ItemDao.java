package com.fight.dt.business.dao;

import com.fight.dt.business.common.beans.Item;

import java.util.List;

/**
 * Created by tpx on 2017/7/28.
 */
public interface ItemDao {
    Item findById(Integer id);

    Item findByItem(Item item);

    List<Item> findAll(String itemId, String sellerId, Integer taskStatus, Integer page, Integer pageSize);

    int insert(Item item);

    int update(Item item);
}
