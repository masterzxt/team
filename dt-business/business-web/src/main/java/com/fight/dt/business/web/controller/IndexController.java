package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/12.
 */
@RestController
@Api("Index")
public class IndexController {

    @Resource
    private UserService userService;

    @ApiOperation(value="首页信息", notes="首页信息")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "success";
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public String adduser(){
        User user  = new User("2","2","2","2","2","2017-07-14","2","2","2","2017-07-14");
        userService.addUser(user);
        return "200";
    }
}
