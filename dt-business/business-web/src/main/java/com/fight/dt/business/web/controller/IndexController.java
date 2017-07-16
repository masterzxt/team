package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.service.UserService;
import com.sun.deploy.net.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tpx on 2017/7/12.
 */
@RestController
@Api("Index")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @ApiOperation(value="首页信息", notes="首页信息")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "success";
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public String adduser(User user){
        userService.insert(user);
        return "200";
    }

    @RequestMapping("/ajaxLogin")
    public void loginPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        logger.info("=================");
        logger.info("=================X-Requested-With="+httpServletRequest.getHeader("X-Requested-With"));
        if (null != httpServletRequest.getHeader("X-Requested-With") && "XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"))) {
            httpServletRequest.getRequestDispatcher("/loginPageAjax").forward(httpServletRequest,httpServletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("/loginPageAjax").forward(httpServletRequest,httpServletResponse);
        }
    }

    @RequestMapping("/loginPageAjax")
    public @ResponseBody String loginPageAjax() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if(authentication.isAuthenticated() && !authentication.getPrincipal().toString().equals("anonymousUser")){
            return  authentication.getPrincipal().toString();
        }else{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("LOGIN");
            Set<SimpleGrantedAuthority> set = new HashSet<SimpleGrantedAuthority>();
            set.add(authority);
            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("user","user",set));
            securityContext.getAuthentication().setAuthenticated(true);
            return "success!";
        }

    }
}
