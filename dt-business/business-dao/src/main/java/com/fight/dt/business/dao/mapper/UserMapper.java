package com.fight.dt.business.dao.mapper;

import com.fight.dt.business.common.beans.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tpx on 2017/7/10.
 */
@Repository
public interface UserMapper {
    User findById(@Param("id") Integer id);

    User findByUsername(@Param("username") String username);

    int insert(User user);
}
