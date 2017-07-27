package com.fight.dt.business.web.controller;

import com.fight.dt.business.common.beans.OptionalShare;
import com.fight.dt.business.common.core.MsgEnum;
import com.fight.dt.business.service.OptionalShareService;
import com.fight.dt.business.service.impl.DtSpringSecurityService;
import io.swagger.annotations.Api;
import org.apache.zookeeper.Op;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by zhangwei on 17/7/27.
 */
@RestController("optionalShareController")
@RequestMapping("/api/opt")
@Api("OptionalShare")
public class OptionalShareController {
    private static final Logger logger = LoggerFactory.getLogger(OptionalShareController.class);

    @Resource
    private OptionalShareService optionalShareService;
    @Resource
    private DtSpringSecurityService dtSpringSecurityService;

    @RequestMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> saveOptionalShare(String shareId) {
        Map<String, Object> resultMap = new HashMap<>();
        OptionalShare optionalShare = new OptionalShare();
        optionalShare.setShareId(shareId);
        optionalShare.setUserId(dtSpringSecurityService.getUser().getId());
        optionalShare.setStatus("1");
        optionalShare.setOperator(dtSpringSecurityService.getUser().getNickname());
        optionalShareService.insert(optionalShare);
        resultMap.put("code", MsgEnum.SUCCESS.getCode());
        resultMap.put("msg", "save optional share success !");
        return resultMap;
    }

    @RequestMapping("/lists")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getOptionShareList() {
        Map<String, Object> resultMap = new HashMap<>();
        List<OptionalShare> optionalShareList = optionalShareService.getByUserId(dtSpringSecurityService.getUser().getId());
        for (OptionalShare optionalShare : optionalShareList) {
            optionalShareService.findShareDetail(optionalShare);
        }
        resultMap.put("code", MsgEnum.SUCCESS.getCode());
        resultMap.put("result", optionalShareList);
        resultMap.put("msg", "get option share list success !");
        return resultMap;
    }
}
