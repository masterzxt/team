package com.fight.dt.business.dao;

import com.fight.dt.business.common.beans.OptionalShare;

import java.util.List;

/**
 * Created by zhangwei on 17/7/27.
 */
public interface OptionalShareDao {
    void insert(OptionalShare optionalShare);

    List<OptionalShare> getByUserId(Integer userId);
}
