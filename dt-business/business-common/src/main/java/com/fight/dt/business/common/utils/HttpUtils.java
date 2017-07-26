package com.fight.dt.business.common.utils;

import com.fight.dt.business.common.constant.UrlConsts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangwei on 17/7/27.
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String DEF_CHATSET = "UTF-8";

    /**
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return String 返回类型
     * @throws
     * @Title: sentPost
     * @Description: post请求
     */
    public static String get(String url, Map<String, String> paramsMap) {
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            if (paramsMap != null && paramsMap.size() != 0) {
                String uri = "?";
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    uri += entry.getKey();
                    uri += "=";
                    uri += entry.getValue();
                    uri += "&";
                }
                uri = uri.substring(0, uri.length()-1);
                url = url.concat(uri);
            }
            logger.info("GET request url:{}", url);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet method = new HttpGet(url);
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (responseText.indexOf("未找到匹配的模板") != -1) {
                logger.debug("http-GET请求：url={} , result={}", url, responseText);
            }
            try {
                response.close();
            } catch (Exception e) {
                logger.error("response关闭出错", e);
            }
        }
        return responseText;
    }

    /**
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return String 返回类型
     * @throws
     * @Title: sentPost
     * @Description: post请求
     */
    public static String post(String url, Map<String, String> paramsMap) {
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(),
                            param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, DEF_CHATSET));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (responseText.indexOf("未找到匹配的模板") != -1) {
                logger.debug("http-POST请求：url={} , result={}", url, responseText);
                logger.warn("未找到模板的参数:{}", paramsMap);
            }
            try {
                response.close();
            } catch (Exception e) {
                logger.error("response关闭出错", e);
            }
        }
        return responseText;
    }

    public static void main(String[] ages) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        //paramsMap.put("list", "sh600000");
        paramsMap.put("list", "sh600699");
        //String responseText = HttpUtils.post("http://hq.sinajs.cn/", paramsMap);
        //String responseText = HttpUtils.get(ShareApi.OPTIONAL_SHARE_API, paramsMap);
        String responseText = HttpUtils.get(UrlConsts.XINLANG_SHARE, paramsMap);
        System.out.println("print test result:" + responseText);
    }
}
