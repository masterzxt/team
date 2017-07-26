package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.OptionalShare;
import com.fight.dt.business.service.OptionalShareService;
import io.swagger.annotations.Api;
import org.apache.zookeeper.Op;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by zhangwei on 17/7/27.
 */
@RestController("optionalShareController")
@RequestMapping("/api/optional/share")
@Api("OptionalShare")
public class OptionalShareController {
    private static final Logger logger = LoggerFactory.getLogger(OptionalShareController.class);

    @Autowired
    private OptionalShareService optionalShareService;

    @RequestMapping("/saveOptionalShare")
    @ResponseBody
    public Map<String, Object> saveOptionalShare(Integer userId, String username, String shareId) {
        Map<String, Object> resultMap = new HashMap<>();
        OptionalShare optionalShare = new OptionalShare();
        optionalShare.setShareId(shareId);
        optionalShare.setUserId(userId);
        optionalShare.setStatus("1");
        optionalShare.setOperator(username);
        optionalShareService.insert(optionalShare);
        resultMap.put("status", 200);
        resultMap.put("msg", "save optional share success !");
        return resultMap;
    }

    @RequestMapping("/getOptionShareList")
    @ResponseBody
    public Map<String, Object> getOptionShareList(Integer userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<OptionalShare> optionalShareList = optionalShareService.getByUserId(userId);
        for (OptionalShare optionalShare : optionalShareList) {
            optionalShareService.findShareDetail(optionalShare);
        }
        resultMap.put("status", 200);
        resultMap.put("result", optionalShareList);
        resultMap.put("msg", "get option share list success !");
        return resultMap;
    }
}
