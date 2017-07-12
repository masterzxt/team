package com.fight.dt.business.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tpx on 2017/7/12.
 */
@RestController
@Api("Index")
public class IndexController {

    @ApiOperation(value="首页信息", notes="首页信息")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "success";
    }
}
