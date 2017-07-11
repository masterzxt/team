package com.fight.dt.business.service.impl;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.dao.UserDao;
import com.fight.dt.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangwei on 17/7/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
