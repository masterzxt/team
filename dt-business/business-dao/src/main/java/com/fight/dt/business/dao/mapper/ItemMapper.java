package com.fight.dt.business.dao.mapper;

import com.fight.dt.business.common.beans.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tpx on 2017/7/28.
 */
@Repository
public interface ItemMapper {
    Item findById(Integer id);

    Item findByItem(Item item);

    int insert(Item item);

    int update(Item item);

    List<Item> findAll(@Param("itemId") String itemId, @Param("sellerId") String sellerId,
                       @Param("taskStatus") Integer taskStatus, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
