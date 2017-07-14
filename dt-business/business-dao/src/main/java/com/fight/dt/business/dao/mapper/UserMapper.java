package com.fight.dt.business.dao.mapper;

import com.fight.dt.business.common.beans.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tpx on 2017/7/10.
 */
public interface UserMapper {
    User getUserById(@Param("id") Integer id);
    User findByUsername(@Param("username") String username);

    Integer addUser(User user);
}
