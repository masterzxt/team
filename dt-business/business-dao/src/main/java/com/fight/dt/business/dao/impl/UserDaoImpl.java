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
    public  User findById(Integer id){
        return userMapper.findById(id);
    }
    @Override
    public  User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
