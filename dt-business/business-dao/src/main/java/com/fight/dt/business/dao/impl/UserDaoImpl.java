package com.fight.dt.business.dao.impl;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.dao.UserDao;
import com.fight.dt.business.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/10.
 */
@Service("userDao")
public class UserDaoImpl implements UserDao{

    @Resource
    private UserMapper userMapper;
    @Override
    public  User getUserById(Integer id){
        return userMapper.getUserById(id);
    }
    @Override
    public  User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }
}
