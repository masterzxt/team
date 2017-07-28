package com.fight.dt.business.service.impl;

import com.fight.dt.business.common.beans.Item;
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
import java.util.Date;
import java.util.Iterator;

/**
 * Created by tpx on 2017/7/28.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService{

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ItemDao itemDao;

    @Override
    public Item findById(Integer id){
        return  itemDao.findById(id);
    }
    @Override
    public Item findByItem(Item item){
        return  itemDao.findByItem(item);
    }
    @Override
    public int insert(Item item){
        return  itemDao.insert(item);
    }
    @Override
    public Item taobao(String itemId,String sellerId){
        if (itemId == null) {
            itemId = "555682960129";
        }
        if (sellerId == null) {
            sellerId = "2957592081";
        }
        Item item = new Item();
        item.setItemId(itemId);
        item.setSellerId(sellerId);
        Item item1 =  itemDao.findByItem(item);
        if(item1 != null){
            return item1;
        }
        HttpMethod httpMethod = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://item.taobao.com/item.htm?id=" + itemId);
        headers.add("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
        HttpEntity<?> httpEntity = new HttpEntity<String>(null, headers);
        String url = "https://detailskip.taobao.com/service/getData/1/p1/item/detail/sib.htm?itemId=" + itemId
                + "&sellerId=" + sellerId
                + "&modules=dynStock,qrcode,viewer,price,duty,xmpPromotion,delivery,activity,fqg,zjys,couponActivity,soldQuantity,originalPrice,tradeContract";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
        logger.info(responseEntity.getBody());
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JSONObject dataJson = jsonObject.getJSONObject("data");
        JSONObject promotionJson = dataJson.getJSONObject("promotion");
        JSONObject promoDataJson = promotionJson.getJSONObject("promoData");
        Iterator iterator = promoDataJson.keySet().iterator();
        BigDecimal minPrice = new BigDecimal("0.00");
        BigDecimal maxPrice = new BigDecimal("0.00");
        JSONArray priceJson;
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            logger.info(key);
            String value = promoDataJson.get(key).toString();
            logger.info(value);
            priceJson = new JSONArray(value);
            if (priceJson.length() >= 1) {
                try {
                    BigDecimal curPrice = new BigDecimal(priceJson.getJSONObject(priceJson.length() - 1).getString("price"));
                    maxPrice = maxPrice.max(curPrice);
                    minPrice = curPrice.min(minPrice);
                    item.setMaxPrice(maxPrice);
                    item.setMinPrice(minPrice);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        itemDao.insert(item);
        return  item;
    }
}
