package com.jjb.cmp.biz.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName test
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/21 16:43
 * Version 1.0
 */
public class test {
    private static  final String ENCORDING = "UTF-8";
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    public static void main(String[] args) {
        String url = "http://10.2.32.29:8080/cmp-app/assets/api/v1/img/id";
        Map<String, Object> params = new HashMap<>();
        params.put("org","000064540000");
        params.put("id_type","I");
        params.put("id_no","360421199543225022");
        params.put("name","吴颖");
        params.put("sys_id","CAS");
        params.put("operator_id","233333");
        long start=System.currentTimeMillis();
        String res_str = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(covertParams2NVPS(params),ENCORDING));
            init();
            CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity,ENCORDING);
            JSONObject jsonObject= JSONObject.parseObject(result);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(res_str);



    }
    private static List<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        List<NameValuePair> pairs = new ArrayList<>();
        params.entrySet().forEach(param -> pairs.add(new BasicNameValuePair(param.getKey(),String.valueOf(param.getValue()))));
        return pairs;
    }
    private static void init(){
        if (poolingHttpClientConnectionManager == null){
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(20);//设置连接池最大连接数
        }
    }
}
