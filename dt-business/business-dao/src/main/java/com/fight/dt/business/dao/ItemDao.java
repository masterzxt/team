package com.fight.dt.business.dao;

import com.fight.dt.business.common.beans.Item;

/**
 * Created by tpx on 2017/7/28.
 */
public interface ItemDao {
    Item findById(Integer id);
    Item findByItem(Item item);
    int insert(Item item);
}
