package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.common.core.MsgEnum;
import com.fight.dt.business.service.UserService;
import com.fight.dt.business.service.impl.DtSpringSecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private DtSpringSecurityService dtSpringSecurityService;

    @ApiOperation(value = "获取用户信息", notes = "用户信息")
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> info() {
        Map map = new HashMap<String, Object>();
        User user = dtSpringSecurityService.getUser();
        if (null != user) {
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            map.put("createTime", user.getCreateTime());
            map.put("code", MsgEnum.SUCCESS.getCode());
            map.put("msg", MsgEnum.SUCCESS.getMsg());
        }
        return map;
    }
}
