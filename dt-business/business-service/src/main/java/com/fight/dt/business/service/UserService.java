package com.fight.dt.business.service;

import com.fight.dt.business.common.beans.User;

/**
 * Created by tpx on 2017/7/10.
 */
public interface UserService {
    User findById(Integer id);
    int insert(User user);
}
