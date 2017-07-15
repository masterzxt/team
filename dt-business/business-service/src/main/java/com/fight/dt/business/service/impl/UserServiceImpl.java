package com.fight.dt.business.service.impl;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.dao.UserDao;
import com.fight.dt.business.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhangwei on 17/7/11.
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public Integer addUser(User user) {
        User user1  = new User("1","1","1","11111","1","2017-07-14","1","1","1","2017-07-14");
        userDao.addUser(user);
        userDao.addUser(user1);
        return 1;
    }
}
