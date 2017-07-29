package com.fight.dt.business.service.impl;

import com.fight.dt.business.common.beans.Item;
import com.fight.dt.business.common.core.ItemTaskStatusEnum;
import com.fight.dt.business.dao.ItemDao;
import com.fight.dt.business.service.ItemService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tpx on 2017/7/28.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ItemDao itemDao;

    @Override
    public Item findById(Integer id) {
        return itemDao.findById(id);
    }

    @Override
    public Item findByItem(Item item) {
        return itemDao.findByItem(item);
    }

    @Override
    public int insert(Item item) {
        return itemDao.insert(item);
    }

    @Override
    public int update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public List<Item> findAll(String itemId, String sellerId, Integer taskStatus, Integer page, Integer pageSize) {
        return itemDao.findAll(itemId, sellerId, taskStatus, page, pageSize);
    }

    @Override
    public void info(Item item) {
        if (item == null || item.getItemId() == null) {
            return;
        }
        if (item.getSellerId() == null) {
            item.setSellerId("2957592081");
        }
        HttpMethod httpMethod = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://item.taobao.com/item.htm?id=" + item.getItemId());
        headers.add("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
        HttpEntity<?> httpEntity = new HttpEntity<String>(null, headers);
        String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=" + item.getItemId()
                + "&sellerId=" + item.getSellerId()
                + "&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,activity,fqg,zjys,couponActivity,soldQuantity,originalPrice,tradeContract";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
        logger.debug("resp=======>"+responseEntity.getBody());
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JSONObject dataJson = null;
        try {
            dataJson = jsonObject.getJSONObject("data");
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            item.setTaskStatus(ItemTaskStatusEnum.FAIL.getCode());
            itemDao.update(item);
            return;
        }

        JSONObject promotionJson = dataJson.getJSONObject("promotion");
        JSONObject promoDataJson = promotionJson.getJSONObject("promoData");
        Iterator iterator = promoDataJson.keySet().iterator();
        BigDecimal minPrice = new BigDecimal("-1");
        BigDecimal maxPrice = new BigDecimal("0.00");
        JSONArray priceJson;
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            logger.debug(key);
            String value = promoDataJson.get(key).toString();
            logger.debug(value);
            priceJson = new JSONArray(value);
            if (priceJson.length() >= 1) {
                try {
                    BigDecimal curPrice = new BigDecimal(priceJson.getJSONObject(priceJson.length() - 1).getString("price"))
                            .setScale(2, BigDecimal.ROUND_DOWN);
                    maxPrice = maxPrice.max(curPrice);
                    if (minPrice.compareTo(new BigDecimal("-1")) > 0) {
                        minPrice = curPrice.min(minPrice);
                    } else {
                        minPrice = curPrice;
                    }
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        item.setMaxPrice(maxPrice);
        item.setMinPrice(minPrice);
        item.setTaskStatus(ItemTaskStatusEnum.SUCCESS.getCode());
        itemDao.update(item);
    }
}
