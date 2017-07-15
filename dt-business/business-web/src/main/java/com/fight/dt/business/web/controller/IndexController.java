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
import java.util.Date;

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
    public String adduser(User user){
        userService.insert(user);
        return "200";
    }
}
