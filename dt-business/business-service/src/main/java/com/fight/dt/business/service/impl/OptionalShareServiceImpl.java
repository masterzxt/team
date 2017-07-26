package com.fight.dt.business.service.impl;

import com.fight.dt.business.common.beans.OptionalShare;
import com.fight.dt.business.common.constant.UrlConsts;
import com.fight.dt.business.common.utils.HttpUtils;
import com.fight.dt.business.dao.OptionalShareDao;
import com.fight.dt.business.service.OptionalShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangwei on 17/7/27.
 */
@Service("optionalShareService")
public class OptionalShareServiceImpl implements OptionalShareService {
    @Autowired
    private OptionalShareDao optionalShareDao;

    @Override
    public void insert(OptionalShare optionalShare) {
        optionalShareDao.insert(optionalShare);
    }

    @Override
    public List<OptionalShare> getByUserId(Integer userId) {
        return optionalShareDao.getByUserId(userId);
    }

    @Override
    public void findShareDetail(OptionalShare optionalShare) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("list", optionalShare.getShareId());
        String response = HttpUtils.get(UrlConsts.XINLANG_SHARE, paramsMap);
        response = response.split("=")[1];
        String[] arrs = response.split(",");
        optionalShare.setNewestPrice(arrs[3]);
        int fluctuation = Integer.valueOf(arrs[3]) - Integer.valueOf(arrs[1]);
        float rose = fluctuation / Float.valueOf(arrs[1]);
        optionalShare.setRose(rose + "");
        optionalShare.setFluctuation(fluctuation + "");
    }
}
