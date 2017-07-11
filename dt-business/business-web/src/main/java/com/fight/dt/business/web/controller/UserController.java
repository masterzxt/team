package com.fight.dt.business.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tpx on 2017/7/10.
 */
@RestController("userController")
@RequestMapping("/api/user")
public class UserController {

    @ApiOperation(value="用户信息", notes="根据User的id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户的id", required = true, dataType = "Long")
    @RequestMapping(value="", method= RequestMethod.GET)
    public String info(Long id) {
        return "success";
    }
}
