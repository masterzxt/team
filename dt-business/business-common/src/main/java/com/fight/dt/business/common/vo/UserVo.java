package com.fight.dt.business.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * Created by tpx on 2017/7/22.
 */
@ApiModel("UserVo")
public class UserVo {

    @ApiParam(name = "用户名",required = true)
    private String username;
    @ApiParam(name = "用户密码",required = true)
    private String password;
    @ApiParam(name = "用户再次输入密码",required = true)
    private String password2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
