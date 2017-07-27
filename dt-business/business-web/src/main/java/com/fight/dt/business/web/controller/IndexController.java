package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.User;
import com.fight.dt.business.common.core.MsgEnum;
import com.fight.dt.business.common.vo.UserVo;
import com.fight.dt.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tpx on 2017/7/12.
 */
@RestController
@Api("Index")
public class IndexController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String ERROR_PATH = "/error";
    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @ApiOperation(value = "错误页", notes = "错误页")
    @RequestMapping(value = ERROR_PATH, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map error() {
        Map map = new HashMap<String, Object>();
        map.put("code", MsgEnum.NOT_FOUND_ERROR.getCode());
        map.put("msg", MsgEnum.NOT_FOUND_ERROR.getMsg());
        return map;
    }

    @ApiOperation(value = "错误页", notes = "错误页")
    @RequestMapping(value = "/loginFail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map loginFail() {
        Map map = new HashMap<String, Object>();
        map.put("code", MsgEnum.USERNAME_NOT_FOUND_ERROR.getCode());
        map.put("msg", MsgEnum.USERNAME_NOT_FOUND_ERROR.getMsg());
        return map;
    }

    @ApiOperation(value = "首页信息", notes = "首页信息")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map index() {
        Map map = new HashMap<String, Object>();
        map.put("code", MsgEnum.SUCCESS.getCode());
        map.put("msg", MsgEnum.SUCCESS.getMsg());
        return map;
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(path = "/reg", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map register(@RequestBody UserVo userVo) {
        Map map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(userVo.getUsername())) {
            map.put("code", MsgEnum.Fail.getCode());
            map.put("msg", "请输入用户名");
            return map;
        }

        if (StringUtils.isEmpty(userVo.getPassword()) || StringUtils.isEmpty(userVo.getPassword2())) {
            map.put("code", MsgEnum.Fail.getCode());
            map.put("msg", "请输入密码");
            return map;
        }
        if (!userVo.getPassword().equals(userVo.getPassword2())) {
            map.put("code", MsgEnum.Fail.getCode());
            map.put("msg", "两次密码输入不一致");
            return map;
        }
        User user = userService.findByUsername(userVo.getUsername());
        if (null != user) {
            map.put("code", MsgEnum.Fail.getCode());
            map.put("msg", "用户名已经存在");
            return map;
        }
        User user1 = new User();
        user1.setUsername(userVo.getUsername());
        user1.setNickname(userVo.getUsername());
        user1.setPassword(passwordEncoder.encode(userVo.getPassword()));
        user1.setCreateTime(new Date());
        userService.insert(user1);
        map.put("code", MsgEnum.SUCCESS.getCode());
        map.put("msg", MsgEnum.SUCCESS.getMsg());
        return map;
    }

    @RequestMapping(path = "/rest", method = RequestMethod.GET)
    @ResponseBody
    public String restTemplate(User user) {
        return restTemplate.getForEntity("https://www.baidu.com", String.class).getBody();
    }

    /**
     * 表示服务端可以接收客户端通过主题“/app/hello”发送过来的消息，客户端需要在主题"/topic/hello"上监听并接收服务端发回的消息
     *
     * @param topic
     * @param headers
     */
    @MessageMapping("/webSocket") //"/webSocket"为WebSocketConf类中registerStompEndpoints()方法配置的
    @SendTo("/topic/greetings")
    public void greeting(@Header("atytopic") String topic, @Headers Map<String, Object> headers) {
        logger.info("connected successfully....");
        logger.info(topic);
        logger.info(headers.toString());
    }

    /**
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
     *
     * @return
     */
    @MessageMapping("/message")
    @SendToUser("/message")
    public String handleSubscribe() {
        logger.info("this is the @SubscribeMapping('/message')");
        return "I am a msg from SubscribeMapping('/message').";
    }

    /**
     * 测试对指定用户发送消息方法
     *
     * @return
     */
    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public Map send(String fromUsername, String toUsername, String content) {
        simpMessagingTemplate.convertAndSendToUser(toUsername, "/message", content);
        Map map = new HashMap<String, Object>();
        map.put("code", MsgEnum.SUCCESS.getCode());
        map.put("msg", MsgEnum.SUCCESS.getMsg());
        return map;
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}
