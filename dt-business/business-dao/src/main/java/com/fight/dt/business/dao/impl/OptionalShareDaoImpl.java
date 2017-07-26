package com.fight.dt.business.dao.impl;

import com.fight.dt.business.common.beans.OptionalShare;
import com.fight.dt.business.dao.OptionalShareDao;
import com.fight.dt.business.dao.mapper.OptionalShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangwei on 17/7/27.
 */
@Service("optionalShareDao")
public class OptionalShareDaoImpl implements OptionalShareDao {
    @Autowired
    private OptionalShareMapper optionalShareMapper;

    @Override
    public void insert(OptionalShare optionalShare) {
        optionalShareMapper.insert(optionalShare);
    }

    @Override
    public List<OptionalShare> getByUserId(Integer userId) {
        return optionalShareMapper.getByUserId(userId);
    }
}
