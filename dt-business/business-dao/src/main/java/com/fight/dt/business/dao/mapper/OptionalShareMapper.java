package com.fight.dt.business.dao.mapper;

import com.fight.dt.business.common.beans.OptionalShare;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangwei on 17/7/27.
 */
@Repository
public interface OptionalShareMapper {
    void insert(OptionalShare optionalShare);

    List<OptionalShare> getByUserId(Integer userId);
}
