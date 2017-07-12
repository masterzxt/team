package com.fight.dt.business.service.impl;

import com.fight.dt.business.dao.UserDao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tpx on 2017/7/12.
 */
@Service("dtUserDetailsService")
public class DtUserDetailsService implements UserDetailsService {


    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("======================="+username);
        System.out.println("======================="+new BCryptPasswordEncoder().encode(username));
        com.fight.dt.business.common.beans.User u = userDao.findByUsername(username);
        if(u == null){
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("LOGIN"));
        User user = new User(u.getUsername(),u.getPassword(),authorities);
        return  user;
    }
}
