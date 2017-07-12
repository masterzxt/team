package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tpx on 2017/7/10.
 */
@RestController("userController")
@RequestMapping("/api/user")
@Api("User")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @ApiOperation(value="用户信息", notes="根据User的id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户的id", required = true, dataType = "Long")
    @RequestMapping(value="/info", method= RequestMethod.GET)
    public String info(Long id) {
        return "success";
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public String getUserById(Integer id){
        User user = userService.getUserById(id);
        logger.info(user.getUsername());
        logger.info(user.getUsername());
        logger.info(user.getPassword());
        return "success!";
    }
}
