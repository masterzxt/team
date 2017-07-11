package com.fight.dt.business.dao;

import com.fight.dt.business.common.beans.User;
import org.springframework.stereotype.Component;

/**
 * Created by tpx on 2017/7/10.
 */
@Component
public interface UserDao {
    User getUserById(Integer id);
}
