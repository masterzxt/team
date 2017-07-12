package com.fight.dt.business.web.conf;

import com.fight.dt.business.service.impl.DtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/12.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Resource
    private DtUserDetailsService dtUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll() //匿名访问
                .anyRequest().authenticated() //认证访问
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()   //登陆页匿名访问
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("11111111111111111");
       /* auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");*/

        auth.userDetailsService(dtUserDetailsService);
    }
}
