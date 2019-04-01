package com.jjb.cmp.biz.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.jjb.cmp.util.HttpClientUtil;
/**
 * @author Bairong
 * @project SmartWeb
 * @description
 * @create 2018-08-22 21:18
 **/
public class HttpTest {
    public static void main(String[] args) throws IOException {
        String test = "{\"productCode\":\"000020180821220744\"," +
                "\"requestParam\":{" +
                "    \"id_card\":\"142223199409090708\"," +
                "    \"phone_no\":\"15154031233\"," +
                "    \"name\":\"王亮\"}," +
                "    \"token\":\"bGK+mrfUtnmwyj5AMfgezO+GKXhlEzNYAY3B5HIg4lKNftUHXFB2bxwCu74XNyQeJcrYl7nEiXL6TdRooPdPFw==\"}";
        HttpPost post = new HttpPost("http://10.109.3.220:8081/decision/sync/get");
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(test, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        String execute = HttpClientUtil.execute(post);

    }
}
