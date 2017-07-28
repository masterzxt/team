package com.fight.dt.business.dao.mapper;

import com.fight.dt.business.common.beans.Item;
import org.springframework.stereotype.Repository;

/**
 * Created by tpx on 2017/7/28.
 */
@Repository
public interface ItemMapper {
    Item findById(Integer id);
    Item findByItem(Item item);
    int insert(Item item);
}
