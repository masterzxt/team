package com.fight.dt.business.dao.impl;

import com.fight.dt.business.common.beans.Item;
import com.fight.dt.business.dao.ItemDao;
import com.fight.dt.business.dao.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/28.
 */
@Service("itemDao")
public class ItemDaoImpl implements ItemDao {

    @Resource
    private ItemMapper itemMapper;
    @Override
    public Item findById(Integer id) {
        return itemMapper.findById(id);
    }

    @Override
    public Item findByItem(Item item) {
        return itemMapper.findByItem(item);
    }

    @Override
    public int insert(Item item) {
        return itemMapper.insert(item);
    }
}
